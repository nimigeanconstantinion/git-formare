package com.projects.formare.services;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.CursRepository;
import com.projects.formare.repository1.CursantRepository;
import com.projects.formare.repository1.NomGrupTintaRepository;
import com.projects.formare.repository1.PersoanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CursantService {
    @Autowired
    CursServices cursServices;
    @Autowired
    CursRepository cursRepository;
    @Autowired
    CursantRepository cursantRepository;
    @Autowired
    PersoanaRepository persoanaRepository;

    @Autowired
    NomGrupTintaRepository nomGrupTintaRepository;

    public CursantService(CursServices cursServices
            ,CursantRepository cursantRepository
    ,CursRepository cursRepository
            ,PersoanaRepository persoanaRepository
    ,NomGrupTintaRepository nomGrupTintaRepository){
        this.cursServices=cursServices;
        this.cursantRepository=cursantRepository;
        this.persoanaRepository=persoanaRepository;
        this.nomGrupTintaRepository=nomGrupTintaRepository;
    }

    public Cursant dtoToCursant(DTOAll dt){
        Cursant nc=new Cursant();
        if(dt.getCnp().trim().length()>0&&dt.getCurs()>0){
            Optional<Cursant> oc=cursantRepository.findCursantByCNPandNrCurs(dt.getCnp(),dt.getCurs());
            if(oc.isEmpty()){
                Optional<Curs> ocr=cursRepository.findCursByNrCurs(dt.getNrc());
                if(ocr.isPresent()){
                    nc.setCurs(ocr.get());
                    Optional<Persoana> op=persoanaRepository.findPersoanaByCNP(dt.getCnp());
                    if(op.isPresent()){
                        nc.setPersoana(op.get());
                    }
                    nc.setNrContract(dt.getNrcontr());
                    nc.setDataContract(dt.getDatacontr());
                    PerioadaSomaj p=dtoToPerSomaj(dt);
                    if(p!=null){
                        nc.setPerioadaSomajList(Set.of(p));
                    }

                }
            }
        }

        return null;
    }

    public NomenclatorGrupTinta dtoToGrTinta(DTOAll dt){
        Optional<NomenclatorGrupTinta> on=nomGrupTintaRepository.getGrTintaByCod(Integer.valueOf(dt.getCodgr()));
        if(on.isPresent()){
            return on.get();
        }
        return null;
    }

    public PerioadaSomaj dtoToPerSomaj(DTOAll dt){
        PerioadaSomaj ps=new PerioadaSomaj();
        if(dt.getNrsomaj().trim().length()>0){
            ps.setNrSomaj(dt.getNrsomaj());
            if(dt.getDatast()!=null){
                ps.setDataStartSomaj(LocalDateTime.of(dt.getDatast().getYear(),dt.getDatast().getMonth(),dt.getDatast().getDay(),0,0,0));
            }
            if(dt.getDatasf()!=null){
                ps.setDataSfSomaj(LocalDateTime.of(dt.getDatasf().getYear(),dt.getDatasf().getMonth(),dt.getDatasf().getDay(),0,0,0));
            }
            if(dt.getSld()!=null&&dt.getSld()==1){
                ps.setSomerLungaDurata(true);
            }else{
                ps.setSomerLungaDurata(false);
            }
//            ps.setDataadd(LocalDateTime.of(dt.getD));
            return ps;
        }
        return null;
    }
}
