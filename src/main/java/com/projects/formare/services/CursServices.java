package com.projects.formare.services;

import com.projects.formare.model.Nomenclator;
import com.projects.formare.model.Persoana;
import com.projects.formare.repository1.*;
import org.springframework.stereotype.Service;

@Service
public class CursServices {
    private CursRepository cursRepository;
    private CursantRepository cursantRepository;
    private PersoanaRepository persoanaRepository;
    private AutorizatieRepository autorizatieRepository;
    private NomenclatorRepository nomenclatorRepository;
    
    public CursServices(CursRepository cursRepository,
                        CursantRepository cursantRepository,
                        PersoanaRepository persoanaRepository,
                        AutorizatieRepository autorizatieRepository,
                        NomenclatorRepository nomenclatorRepository){
        this.autorizatieRepository=autorizatieRepository;
        this.cursantRepository=cursantRepository;
        this.cursRepository=cursRepository;
        this.nomenclatorRepository=nomenclatorRepository;
        this.persoanaRepository=persoanaRepository;
    }
    
    public Persoana addPersoana(){
        return null;
    }
    
}