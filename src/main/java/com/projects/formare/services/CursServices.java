package com.projects.formare.services;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
import com.projects.formare.repository2.DTOAllRepo;
import com.projects.formare.utils.MatchString;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursServices {
    private CursRepository cursRepository;
    private CursantRepository cursantRepository;
    private PersoanaRepository persoanaRepository;
    private AutorizatieRepository autorizatieRepository;
    private NomenclatorRepository nomenclatorRepository;

    private NomStudiiRepository nomStudiiRepository;
    private DTOAllRepo dtoAllRepo;

    private LocalitateRepository localitateRepository;


    public CursServices(CursRepository cursRepository,
                        CursantRepository cursantRepository,
                        PersoanaRepository persoanaRepository,
                        AutorizatieRepository autorizatieRepository,
                        NomenclatorRepository nomenclatorRepository,
                        DTOAllRepo dtoAllRepo,
                        NomStudiiRepository nomStudiiRepository,
                        LocalitateRepository localitateRepository) {
        this.autorizatieRepository = autorizatieRepository;
        this.cursantRepository = cursantRepository;
        this.cursRepository = cursRepository;
        this.nomenclatorRepository = nomenclatorRepository;
        this.persoanaRepository = persoanaRepository;
        this.dtoAllRepo = dtoAllRepo;
        this.nomStudiiRepository = nomStudiiRepository;
        this.localitateRepository = localitateRepository;
    }


    public void addPersoane() throws CloneNotSupportedException {
        try {

            final List<DTOAll> lista = dtoAllRepo.findAll();
            System.out.println(lista.size());
            List<Persoana> listap = new ArrayList<>();
            int k = 0;
            for (DTOAll e : lista) {
                Persoana p = new Persoana();

                System.out.println("Index ="+lista.indexOf(e)+"/"+lista.size());
                p.setCnp(e.getCnp());
                p.addNume(e.getNume());
                p.setPrenume(e.getPrenume());
                p.setPrenumeMama(e.getPrenmama());
                p.setPrenumeTata(e.getPrentata());
                p.setLocalitateNastere(e.getLocn());
                p.setJudetNastere(e.getJudn());
                p.setAdresaList(new ArrayList<>());
                int codStudii = 0;
                if (e.getCodstudii().isEmpty()) {
                    codStudii = 0;
                } else {
                    codStudii = Integer.valueOf(e.getCodstudii());

                }
                Studii s = new Studii();
                Optional<NomenclatorStudii> ss = nomStudiiRepository.getNomStudiiByCod(codStudii);
                if (ss.isPresent()) {

                    s.setPersoana(p);
                    s.setNomStudii(ss.get());
                    s.setDataAdd(e.getData1());
                    p.addStud(s);
                }


                if (e.getCnp().trim().length() >= 13) {

                    Adresa adres = new Adresa();

                    adres.setLocaInf(e.getLocinf());
                    adres.setLocaSup(e.getLocsup());
                    adres.setJudet(e.getJud());
                    adres.setStrada(e.getStrada());
                    adres.setNrStrada(e.getNrstr());
                    adres.setBlCasa(e.getBloc());
                    adres.setEt(e.getEt());
                    adres.setAp(e.getAp());
                    adres.setTel(e.getTel());
                    adres.setEmail("");
                    adres.setDateAdd(new Date());
//                    Optional<Localitate> siru = localitateRepository.findByLocalitate();
                    int codJud = 0;
                    codJud = localitateRepository.findJud(e.getJud());
//                    System.out.println("Judet="+e.getJud()+" cu codul="+codJud);
                    List<Localitate> siru = localitateRepository.findAllByLocaCodJud(codJud, e.getLocinf());
//                    System.out.println("---- adaug Loca=" + e.getLocinf() + " din jud=" + e.getJud());
                    if (siru.size() > 0) {
                        adres.setLocalitate(siru.get(0));
//                       System.out.println("-------din siruta=" + adres.getLocalitate().getDenumireLocalitate());

                    }
                    p.addAdresa(adres);


                    Optional<Persoana> isp = persoanaRepository.findPersoanaByCNP(p.getCnp());

                    if (isp.isEmpty()) {
                        persoanaRepository.saveAndFlush(p);
                    } else {
                        Persoana newp = (Persoana) isp.get().clone();

                        if (newp.getNume().contains(e.getNume()) == false) {
                            newp.addNume(e.getNume());

                        }
                        if (newp.getStudiiList().stream()
                                .filter(o -> o.getNomStudii().getCodStudii() == Integer.valueOf(e.getCodstudii()))
                                .collect(Collectors.toList()).size() > 0) {
                            s.setPersoana(newp);
                            newp.addStud(s);
                        }

                        List<Adresa> listaa = new ArrayList<>();
                        newp.getAdresaList().stream().filter(ad -> {
                            if (ad.getLocaInf().equals(e.getLocinf())
                                    || ad.getNrStrada().equals(e.getNrstr())
                                    || ad.getLocaSup().equals(e.getLocsup())
                                    || ad.getBlCasa().equals(e.getBloc())) {
                                return true;
                            }
                            listaa.add(ad);
                            return false;
                        });

                        if (listaa.size() == 0) {
                            newp.addAdresa(p.getAdresaList().get(0));
                        }
                        persoanaRepository.save(newp);
//                  persoanaRepository.flush();
                    }

                    Optional<Curs> curs = cursRepository.findCursByNrCurs(e.getCurs());



//                    System.out.println(p.getNume().get(0) + " " + p.getCnp() + " loca=" + p.getAdresaList().get(0).getLocalitate().getDenumireLocalitate());
//                    k++;
                    if (k > 100) {
                        break;
                    }
                }


            }

            System.out.println(listap.size());
        } catch (Exception e) {
            throw e;
        }


    }


}