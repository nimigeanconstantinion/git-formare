package com.projects.formare.services;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
import com.projects.formare.repository2.DTOAllRepo;
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


    public CursServices(CursRepository cursRepository,
                        CursantRepository cursantRepository,
                        PersoanaRepository persoanaRepository,
                        AutorizatieRepository autorizatieRepository,
                        NomenclatorRepository nomenclatorRepository,
                        DTOAllRepo dtoAllRepo,
                        NomStudiiRepository nomStudiiRepository){
        this.autorizatieRepository=autorizatieRepository;
        this.cursantRepository=cursantRepository;
        this.cursRepository=cursRepository;
        this.nomenclatorRepository=nomenclatorRepository;
        this.persoanaRepository=persoanaRepository;
        this.dtoAllRepo=dtoAllRepo;
        this.nomStudiiRepository=nomStudiiRepository;
    }



    public void addPersoane() throws CloneNotSupportedException {
      try{

          final List<DTOAll> lista=dtoAllRepo.findAll();
          System.out.println(lista.size());
          List<Persoana> listap=new ArrayList<>();
          int k=0;
          for (DTOAll e: lista) {
              Persoana p=new Persoana();


              p.setCnp(e.getCnp());
              p.addNume(e.getNume());
              p.setPrenume(e.getPrenume());
              p.setPrenumeMama(e.getPrenmama());
              p.setPrenumeTata(e.getPrentata());
              p.setLocalitateNastere(e.getLocn());
              p.setJudetNastere(e.getJudn());
              int codStudii=0;
              if(e.getCodstudii().isEmpty()){
                  codStudii=0;
              }else{
                  codStudii=Integer.valueOf(e.getCodstudii());

              }
              Studii s=new Studii();
              Optional<NomenclatorStudii> ss=nomStudiiRepository.getNomStudiiByCod(codStudii);
              if(ss.isPresent()){

                  s.setPersoana(p);
                  s.setNomStudii(ss.get());
                  s.setDataAdd(e.getData1());
                  p.addStud(s);
              }

              Adresa adresa=new Adresa();
              adresa.setLocalitateInf(e.getLocinf());
              adresa.setLocaSup(e.getLocsup());
              adresa.setJudet(e.getJud());
              adresa.setStrada(e.getStrada());
              adresa.setNrStrada(e.getNrstr());
              adresa.setBlCasa(e.getBloc());
              adresa.setEt(e.getEt());
              adresa.setAp(e.getAp());
              adresa.setTel(e.getTel());
              adresa.setEmail("");

              if(e.getCnp().trim().length()>=13){

                  Optional<Persoana> isp=persoanaRepository.findPersoanaByCNP(p.getCnp());

                  if(isp.isEmpty()){
                      persoanaRepository.saveAndFlush(p);
                  }else{
                      Persoana newp=(Persoana) isp.get().clone();

                      if(newp.getNume().contains(e.getNume())==false){
                          newp.addNume(e.getNume());

                      }
                      if(newp.getStudiiList().stream()
                              .filter(o->o.getNomStudii().getCodStudii()==Integer.valueOf(e.getCodstudii()))
                              .collect(Collectors.toList()).size()>0){
                          s.setPersoana(newp);
                          newp.addStud(s);
                      }
                      persoanaRepository.save(newp);
//                  persoanaRepository.flush();
                  }

                  System.out.println(p.getNume().get(0)+" "+p.getCnp());
                  k++;
                  if(k>10){
                      break;
                  }
              }



          }


          System.out.println(listap.size());
      }catch (Exception e){
          throw e;
      }


    }


    
}