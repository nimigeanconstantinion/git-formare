package com.projects.formare.services;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
import com.projects.formare.repository2.DTOAllRepo;
//import com.projects.formare.utils.MatchString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projects.formare.util.MatchString;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddCursService {
    @Autowired
    private CursRepository cursRepository;
    @Autowired
    private CursantRepository cursantRepository;
    private DTOAllRepo dtoAllRepo;
    private NomenclatorRepository nomenclatorRepository;
    private AutorizatieRepository autorizatieRepository;
    private FurnizorRepository furnizorRepository;
    private FinantareRepository finantareRepository;
    private PersoanaRepository persoanaRepository;

    private NomGrupTintaRepository nomGrupTintaRepository;

    public AddCursService(CursRepository cursRepository,
                          CursantRepository cursantRepository,
                          DTOAllRepo dtoAllRepo,
                          NomenclatorRepository nomenclatorRepository,
                          AutorizatieRepository autorizatieRepository,
                          FurnizorRepository furnizorRepository,
                          FinantareRepository finantareRepository,
                          PersoanaRepository persoanaRepository,
                          NomGrupTintaRepository nomGrupTintaRepository) {
        this.cursantRepository = cursantRepository;
        this.cursRepository = cursRepository;
        this.dtoAllRepo = dtoAllRepo;
        this.nomenclatorRepository = nomenclatorRepository;
        this.autorizatieRepository = autorizatieRepository;
        this.furnizorRepository = furnizorRepository;
        this.finantareRepository = finantareRepository;
        this.persoanaRepository = persoanaRepository;
        this.nomGrupTintaRepository = nomGrupTintaRepository;
    }

    public void addCursuri() {
        List<Integer> lista = dtoAllRepo.getAllCursuri();
        for (Integer c : lista) {
            Optional<Curs> curs = cursRepository.findCursByNrCurs(c);
            if (curs.isEmpty()) {
                System.out.println(" Index " + lista.indexOf(c) + "/" + lista.size());

                List<DTOAll> cd = dtoAllRepo.getDTCursbyNrc(c);
                if (cd.size() > 0) {
                    System.out.println(cd.get(0).toString());

                    addCurs(dtoCursToCurs(cd.get(0)));

                }
                System.out.printf("Am adaugat curs=" + c);
            } else {
                System.out.println("Era ");
            }
        }

    }

    public Curs dtoCursToCurs(DTOAll dt) {
        System.out.println("In DTOToCurs");
        Curs curs = new Curs();
        curs.setNrCurs(dt.getCurs());
        curs.setDataStart(dt.getDatast());
        curs.setDataSfarsit(dt.getDatasf());
        curs.setDataExamen(dt.getDataex());
        if (dt.getNrautc().length() > 0) {
            Autorizatie a = autorizatieRepository.findByNrandData(dt.getNrautc(), dt.getDataut()).get();

            curs.setAutorizatie(a);
            curs.setNomenclator(a.getNomenclator());

        } else {
            Nomenclator n = new Nomenclator();

            if (dt.getCornom().length() > 0) {
                n = nomenclatorRepository.findByCodC(dt.getCornom()).get();
            } else {
                n = getBestNomenclatorComune(dt.getDencalif());
            }
            curs.setNomenclator(n);
        }

        System.out.println("Flag 11");
        curs.setNrProcesVerbalExamen(dt.getNrpv());
        curs.setDataProcesVerbalExamen(dt.getDatapv());

        curs.setNrDispozitie(dt.getNrdisp());
        curs.setDataDisp(dt.getDatadisp());

        curs.setCodFiscalOrg(dt.getCodforg());
        curs.setOrganizator(dt.getOrg());
        if (dt.getOrg().toUpperCase().contains("AGENTIA")) {
            curs.setFurnizor(furnizorRepository.getFurnizByPartDen("AGENTIA JUDETEANA").get(0));
            curs.setTipFurnizor(TipFurnizor.AJOFM);

        } else if (dt.getOrganizat().contains("REGIONAL")) {
            curs.setFurnizor(furnizorRepository.getFurnizByPartDen("CLUJ").get(0));
            curs.setTipFurnizor(TipFurnizor.CRFPA);
        } else {
            curs.setTipFurnizor(TipFurnizor.FURNIZOR);
        }
        System.out.println("Flag 12");

        curs.setResponsabil(dt.getResponsab());

        curs.setOreTotal(dt.getNrore());
        curs.setOreTeorie(dt.getOret());
        curs.setOrePractica(dt.getOrep());
        curs.setOrganizator(dt.getOrg());

        System.out.println("-------------------------Flag 00");

        List<Furnizor> furniz = furnizorRepository.getFurnizByPartDen(dt.getOrg().trim().toUpperCase());
        if (furniz.size() > 0) {
            curs.setFurnizor(furniz.get(0));
        }

        System.out.println("-------------------------Flag 01");


        String f = dt.getFinantare();
        if (f.trim().length() == 0 || f.trim().toUpperCase().equals("BAS")) {
            List<Finantare> listaf = finantareRepository.getFondFinantareByDen("BAS".trim().toUpperCase());
            curs.setFinantare(listaf.get(0));
        } else {
            List<Finantare> listaf = finantareRepository.getFondFinantareByDen(f.trim().toUpperCase());
            curs.setFinantare(listaf.get(0));
        }

        System.out.println("-------------------------Flag 02 " + dt.getTip());

        if (dt.getTip().trim().length() > 0) {
            List<TipCurs> listTip = Arrays.stream(TipCurs.values()).filter(q -> q.getLabel(q).contains(dt.getTip().toLowerCase())).toList();
            if (listTip.size() > 0) {
                System.out.println("listTip = " + listTip.get(0).name());
                curs.setTipCurs(listTip.get(0));
            }
        } else {
            if (dt.getCornom().contains(".")) {
                curs.setTipCurs(TipCurs.CALIFICARE);
            } else if (dt.getCornom().length() > 0) {
                LocalDateTime localDate = LocalDateTime.of(2004, 12, 31, 0, 0, 0);
                Date dataFixa = Date.from(localDate.toInstant(ZoneOffset.UTC));


                if (Integer.valueOf(dt.getCornom().trim().charAt(0)) > 3 && dt.getDatast().after(dataFixa)) {
                    curs.setTipCurs(TipCurs.CALIFICARE);
                } else {
                    curs.setTipCurs(TipCurs.INITIERE);
                }
            } else {
                curs.setTipCurs(TipCurs.COMPETENTE_COMUNE);
            }
        }

        System.out.println("-------------------------Flag 03");
        if (curs.getTipCurs().name().equals("CALIFICARE")) {
            curs.setTipCertificat(TipCertificat.CALIFICARE);
        } else {
            curs.setTipCertificat(TipCertificat.ABSOLVIRE);
        }

        return curs;
    }

    public void addCurs(Curs curs) {

        Optional<Curs> c = cursRepository.findCursByNrCurs(curs.getNrCurs());
        if (c.isEmpty()) {
            cursRepository.saveAndFlush(curs);
        }
    }


    public TipCertificat getTipCertifByTipCurs(String cornom, String tipc) {
        if (tipc.toLowerCase().contains("calificare")) {
            return TipCertificat.CALIFICARE;
        } else if (tipc.toLowerCase().contains("initiere")
                || tipc.toLowerCase().contains("special")
                || tipc.toLowerCase().contains("compet")) {
            return TipCertificat.ABSOLVIRE;
        } else if (cornom.contains(".")) {
            return TipCertificat.CALIFICARE;
        } else {
            return TipCertificat.ABSOLVIRE;
        }


    }

    public Nomenclator getBestNomenclatorComune(String comp) {
        List<Nomenclator> n =
                nomenclatorRepository.getCompetenteComune().stream().sorted((s1, s2) ->
                        Integer.compare(getRank(MatchString.getWords(comp), s1.getDenumire())
                                , getRank(MatchString.getWords(comp), s2.getDenumire()))).toList();
        if (n.size() > 0) {
            return n.get(n.size() - 1);
        }
        return null;
    }

    public int getRank(List<String> cuvinte, String compet) {
        Map<String, Integer> frecventa = new HashMap<>();
        for (String c : cuvinte) {
            frecventa.put(c.toLowerCase(), 0);

        }
        int rank = 0;

        List<String> surse = MatchString.getWords(compet);
        for (String s : surse) {
            if (frecventa.get(s.toLowerCase()) != null) {
                frecventa.put(s, frecventa.get(s) + 1);
                rank++;
            }
        }
        return rank;
    }

    public Cursant dtoToCursant(DTOAll cursant) {
        Cursant newC = new Cursant();
        Optional<Persoana> p = persoanaRepository.findPersoanaByCNP(cursant.getCnp());
        if (p.isPresent()) {
            Persoana addP = p.get();
            newC.setPersoana(addP);
//            ?ewC.setCursantGrupTintaList(nomGrupTintaRepository.getGrTintaByCod((Integer) cursant.getCodgr()));

        }
        return null;
    }
}