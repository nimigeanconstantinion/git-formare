package com.projects.formare.services;

import com.projects.formare.dto.DTAutorizatie;
import com.projects.formare.model.Autorizatie;
import com.projects.formare.model.Competenta;
import com.projects.formare.model.Nomenclator;
import com.projects.formare.model.TipCurs;
import com.projects.formare.repository1.AutorizatieRepository;
import com.projects.formare.repository1.NomenclatorRepository;
import com.projects.formare.repository2.DTOAutorizRepo;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DTAutorizService {

    private DTOAutorizRepo dtoAutorizRepo;

    private AutorizatieRepository autorizatieRepository;
    private NomenclatorRepository nomenclatorRepository;

    public DTAutorizService(DTOAutorizRepo dtoAutorizRepo,
                            AutorizatieRepository autorizatieRepository,
                            NomenclatorRepository nomenclatorRepository) {
        this.dtoAutorizRepo = dtoAutorizRepo;
        this.autorizatieRepository = autorizatieRepository;
        this.nomenclatorRepository = nomenclatorRepository;
    }

    public void addAut(DTAutorizatie dta) throws RuntimeException {
        System.out.println("***********************************");
        Optional<Autorizatie> auto = autorizatieRepository.findByNrandData(dta.getNR(), dta.getDATA_RNFPA());


        try {
            if (!auto.isPresent()) {
                System.out.println("nu am gasit autoriz");

                Autorizatie aut = new Autorizatie();
                aut.setNrAutorizatie(dta.getNR());

                aut.setDataAutorizatie(dta.getDAT_AUT());
                aut.setNrRNFPA(dta.getNR_RNFPA());
                aut.setDataRNFPA(dta.getDATA_RNFPA());

                String cod = "";
                if (dta.getCOD_NOM().length() == 0) {
                    if (dta.getCOR().length() > 0) {
                        cod = dta.getCOR();
                    }
                } else {
                    cod = dta.getCOD_NOM();
                }

                Nomenclator nom = getNomCalifByCodDen(cod, dta.getCALIFICARE());
                System.out.println("nomenc=" + nom.getDenumire());
                if (nom != null) {
                    aut.setNomenclator(nom);
                }
                System.out.println("am adaugat");
                aut.setBazaLegala(dta.getBAZA_LEG());
                aut.setCondAcces(dta.getCOND_ACCES());
                aut.setNivel(0);
                autorizatieRepository.saveAndFlush(aut);
                aut=autorizatieRepository.findByNrandData(aut.getNrAutorizatie(),aut.getDataAutorizatie()).get();
                for (int i = 1; i < 26; i++) {
                    String numeAtribut = "getCOMPET" + i;
                    String valoareAtribut = (String) dta.getClass().getMethod(numeAtribut).invoke(dta);
                    if (valoareAtribut.length() > 0) {

                        aut.addCompetenta(new Competenta(aut,valoareAtribut));
                    }
                }


                aut.setNotare(dta.getNOTARE());
                aut.setOrePractica(Integer.valueOf(dta.getORE_PRACT().length() > 0 ? dta.getORE_PRACT() : "0"));
                aut.setOreTeorie(Integer.valueOf(dta.getORE_TEORIE().length() > 0 ? dta.getORE_TEORIE() : "0"));

                aut.setTotalOre(aut.getOrePractica() + aut.getOreTeorie());

                TipCurs tip = TipCurs.getByLabel(dta.getTIP().trim());
                aut.setTipCurs(tip);
                System.out.printf("Am obtinut=" + tip.name());
                //                aut.setTipCurs(tip);
                aut.setDateAdd(LocalDateTime.now());

                System.out.println("------dinservice add auto " + aut.getNrAutorizatie());
                autorizatieRepository.saveAndFlush(aut);
            }


        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

    public Nomenclator getNomCalifByCodDen(String cod, String den) {
        if (cod.length() > 0) {
            Optional<Nomenclator> nom = nomenclatorRepository.findByCodC(cod);
            if (nom.isPresent()) {
                return nom.get();
            }

        } else {
            List<Nomenclator> nom = nomenclatorRepository.findByName(den);
            if (nom.size() > 1) {
                System.out.println("Atentie");
            } else if (nom.size() == 1) {
                return nom.get(0);
            }
            return null;
        }
        return null;
    }
}