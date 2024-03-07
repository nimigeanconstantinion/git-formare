package com.projects.formare.services;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
import com.projects.formare.repository2.DTOAllRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddCursService {
    private CursRepository cursRepository;
    private CursantRepository cursantRepository;
    private DTOAllRepo dtoAllRepo;
    private NomenclatorRepository nomenclatorRepository;
    private AutorizatieRepository autorizatieRepository;
    private FurnizorRepository furnizorRepository;
    private FinantareRepository finantareRepository;

    public AddCursService(CursRepository cursRepository,
                          CursantRepository cursantRepository,
                          DTOAllRepo dtoAllRepo,
                          NomenclatorRepository nomenclatorRepository,
                          AutorizatieRepository autorizatieRepository,
                          FurnizorRepository furnizorRepository,
                          FinantareRepository finantareRepository) {
        this.cursantRepository = cursantRepository;
        this.cursRepository = cursRepository;
        this.dtoAllRepo = dtoAllRepo;
        this.nomenclatorRepository = nomenclatorRepository;
        this.autorizatieRepository = autorizatieRepository;
        this.furnizorRepository = furnizorRepository;
        this.finantareRepository = finantareRepository;
    }

    public void addCursuri() {
        List<Integer> lista = dtoAllRepo.getAllCursuri();
        for (Integer c : lista) {
            Optional<Curs> curs = cursRepository.findCursByNrCurs(c);
            if (curs.isEmpty()) {
                System.out.println("Index " + lista.indexOf(c) + "/" + lista.size());

                List<DTOAll> cd = dtoAllRepo.getDTCursbyNrc(c);
                addCurs(dtoCursToCurs(cd.get(0)));
                System.out.printf("Am adaugat curs=" + c);
            }
        }

    }

    public Curs dtoCursToCurs(DTOAll dt) {

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
                n = nomenclatorRepository.findByCompCom(dt.getDencalif()).get(0);
            }
            curs.setNomenclator(n);
        }


        curs.setNrProcesVerbalExamen(dt.getNrpv());
        curs.setDataProcesVerbalExamen(dt.getDatapv());

        curs.setNrDispozitie(dt.getNrdisp());
        curs.setDataDisp(dt.getDatadisp());

        curs.setOrganizator(dt.getOrganizat());
        curs.setResponsabil(dt.getResponsab());

        curs.setOreTotal(dt.getNrore());
        curs.setOreTeorie(dt.getOret());
        curs.setOrePractica(dt.getOrep());

        List<Furnizor> furniz = furnizorRepository.getFurnizByPartDen(dt.getOrg().trim().toUpperCase());
        if (furniz.size() > 0) {
            curs.setFurnizor(furniz.get(0));
        }

        String f = dt.getFinantare();
        if (f.trim().length() == 0 || f.trim().toUpperCase().equals("BAS")) {
            List<Finantare> listaf = finantareRepository.getFondFinantareByDen("BAS".trim().toUpperCase());
            curs.setFinantare(listaf.get(0));
        } else {
            List<Finantare> listaf = finantareRepository.getFondFinantareByDen(f.trim().toUpperCase());
            curs.setFinantare(listaf.get(0));
        }

        if (dt.getTip().trim().length() > 0) {
            List<String> listTip = Arrays.stream(TipCurs.values()).map(TipCurs::name).filter(q -> q.contains(dt.getTip().trim())).collect(Collectors.toUnmodifiableList());
            if (listTip.size() > 0) {
                curs.setTipCurs(TipCurs.getByLabel(listTip.get(0)));
            }
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

}