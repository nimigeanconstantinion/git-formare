package com.projects.formare.services;

//import com.projects.formare.constante.*;
import com.projects.formare.constants.Constante;
import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
import com.projects.formare.repository2.DTOAllRepo;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CursServices {
    private CursRepository cursRepository;
    private CursantRepository cursantRepository;
    private PersoanaRepository persoanaRepository;
    private AutorizatieRepository autorizatieRepository;
    private NomenclatorRepository nomenclatorRepository;

    private NomGrupTintaRepository nomGrupTintaRepository;
    private NomStudiiRepository nomStudiiRepository;
    private DTOAllRepo dtoAllRepo;

    private LocalitateRepository localitateRepository;
    private NomenclatorStariRepository nomenclatorStariRepository;
    private CarteIdentitateRepository carteIdentitateRepository;

    private AdresaRepository adresaRepository;
    private StudiiRepository studiiRepository;



    private final EntityManagerFactory entityManagerFactory;
//    @Autowired
//    @Qualifier("criteriaBuilderFactory1")
//    private CriteriaBuilderFactory criteriaBuilderFactory;
  @Autowired
    public CursServices(CursRepository cursRepository,
                        CursantRepository cursantRepository,
                        PersoanaRepository persoanaRepository,
                        AutorizatieRepository autorizatieRepository,
                        NomenclatorRepository nomenclatorRepository,
                        DTOAllRepo dtoAllRepo,
                        NomStudiiRepository nomStudiiRepository,
                        LocalitateRepository localitateRepository,
                        NomGrupTintaRepository nomGrupTintaRepository,
                        NomenclatorStariRepository nomenclatorStariRepository,
                        CarteIdentitateRepository carteIdentitateRepository,
                        StudiiRepository studiiRepository,
                        AdresaRepository adresaRepository,
                        @Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory
                        ) {
        this.autorizatieRepository = autorizatieRepository;
        this.cursantRepository = cursantRepository;
        this.cursRepository = cursRepository;
        this.nomenclatorRepository = nomenclatorRepository;
        this.persoanaRepository = persoanaRepository;
        this.dtoAllRepo = dtoAllRepo;
        this.nomStudiiRepository = nomStudiiRepository;
        this.localitateRepository = localitateRepository;
        this.nomGrupTintaRepository = nomGrupTintaRepository;
        this.nomenclatorStariRepository = nomenclatorStariRepository;
        this.carteIdentitateRepository = carteIdentitateRepository;
        this.studiiRepository=studiiRepository;
        this.adresaRepository=adresaRepository;
        this.entityManagerFactory=entityManagerFactory;
        //        this.entityManagerFactory=entityManagerFactory;
//        this.criteriaBuilderFactory=criteriaBuilderFactory;
//        this.criteriaBuilderFactory=criteriaBuilderFactory1;

    }


    public void addPersoane(int start, int stop) throws CloneNotSupportedException {
        final List<DTOAll> lista = dtoAllRepo.findAll();
        System.out.println(lista.size());
        int k = 0;
        boolean upd=false;
        Persoana px = new Persoana();
        Persoana old = new Persoana();
        List<Studii> sxList=new ArrayList<>();

        Adresa ax=new Adresa();
        Studii sx=new Studii();
        CarteIdentitate cx=new CarteIdentitate();
        for (DTOAll e : lista) {
            k++;
            System.out.println("-------------------k="+k+" CNP lista=" + e.getCnp());
            if (e.getCnp().trim().length() >= 13) {
                if (k >= start && k <= stop) {

//                    System.out.println("------------------ k=" + k);
                    px = dtoToPers(e);
                    sx=dtoToStudii(e);
                    cx=dtoToCarteIdentitate(e);
                    ax=dtoToAdresa(e);
                    upd=false;
//                    System.out.println(px.toString());
                    Optional<Persoana> oper = persoanaRepository.findPersoanaByCNP(px.getCnp());

//                    System.out.println("Nou de adaugat? " +oper.isPresent());
                    if (oper.isPresent()==false) {
//                        System.out.println("Adaug");
                        if(sx!=null){
                            px.addStud(sx);

                        }
                        if(cx!=null){
                            px.addCI(cx);

                        }
                        if(ax!=null){
                            px.addAdresa(ax);

                        }

                        persoanaRepository.saveAndFlush(px);
//                        px=persoanaRepository.findPersoanaByCNP(px.getCnp()).get();
//
//                        if(sx!=null&&sx.getNomStudii()!=null){
//                            sx.setPersoana(px);
//                            studiiRepository.saveAndFlush(sx);
//                        }
//
//                        if(ax!=null&&ax.getLocaInf().length()>0){
//                            ax.setPersoana(px);
//                            adresaRepository.saveAndFlush(ax);
//                        }
//
//                        if(cx!=null&&cx.getNumar().length()>0){
//                            cx.setPersoana(px);
//                            carteIdentitateRepository.saveAndFlush(cx);
//                        }
                    } else {
//                        System.out.println("Exista adaug doar date");
//                        System.out.println(oper.get().toString());
                        old=oper.get();
//                        System.out.println("****Pers exista "+old.toString());
//                        System.out.println("Nume nou="+px.getNume().stream().findFirst().get().toString());
//                        System.out.println("nume vechi="+old.getNume().stream().findFirst().get().toString());

                        if (!old.getNume().contains(px.getNume().stream().findFirst().get())) {
                            old.addNume(px.getNume().stream().findFirst().get());
                            old.addNume(px.getNume().stream().findFirst().get());
//                            persoanaRepository.saveAndFlush(old);
                            System.out.println("Updated nume");
                            upd=true;
                        }

//                        System.out.println("Passed Nume");
//                        sx.setPersoana(old);
                        if (sx!=null){

                            sx.setPersoana(old);
//                            System.out.println(sx.toString());
                            if(sx.getNomStudii()!=null
                                    &&!studiiRepository.findStudiiByCNP(old.getCnp()).contains(sx)){
                                old.addStud(sx);
                                upd=true;
                                System.out.println("Updated studii");

                            }
                        }
//                        System.out.println("Passed Studii");


//                       System.out.println("Adresa e "+ax.getLocaInf());
                        if (ax!=null){
//                            System.out.println(ax.toString());
                            ax.setPersoana(old);

                            if(ax.getLocaInf().length()>0
                                    &&!adresaRepository.findAdresaByCNP(old.getCnp()).contains(ax)){
                                        old.addAdresa(ax);
                                    System.out.println("Udated address");

                                    upd=true;
                            }
                        }

//                        System.out.println("Passed Adrese");

                        if (cx!=null){
                            cx.setPersoana(old);
                            if(!carteIdentitateRepository.findCarteIdentitateByCNP(old.getCnp()).contains(cx)){
                                    old.addCI(cx);
                                System.out.println("Updated ci");
                                upd=true;
                            }
                        }
//                        System.out.println("Passed CI");

                        if(upd==true){
                            System.out.println("Updated some");
                        }else{
                            System.out.println("Nothing to update");
                        }
                        persoanaRepository.saveAndFlush(old);
                    }

                }

            }
        }
    }

    public Adresa dtoToAdresa(DTOAll dt){
        Adresa adres=new Adresa();
            adres.setLocaInf(dt.getLocinf());
            adres.setLocaSup(dt.getLocsup());
            adres.setJudet(dt.getJud());
            adres.setStrada(dt.getStrada());
            adres.setNrStrada(dt.getNrstr());
            adres.setBlCasa(dt.getBloc());
            adres.setEt(dt.getEt());
            adres.setScara(dt.getScara());
            adres.setAp(dt.getAp());
            adres.setTel(dt.getTel());
            adres.setEmail("");
            adres.setDateAdd(dt.getDatast());

            int codJud = 0;
            codJud = localitateRepository.findJud(dt.getJud());
            List<Localitate> siru = localitateRepository.findAllByLocaCodJud(codJud, dt.getLocinf());
            if (siru.size() > 0) {
                adres.setLocalitate(siru.get(0));

            }
            if (dt.getUrbrur().trim().toUpperCase().equals("U") ||
                    dt.getUrbrur().trim().toUpperCase().equals("M") ||
                    dt.getUrbrur().trim().toUpperCase().equals("O")) {
                adres.setMediuLocalitate(MediuLocalitate.URBAN);
            } else {
                if (siru.size() > 0 && Constante.URBAN.contains(siru.get(0).getTip())) {
                    adres.setMediuLocalitate(MediuLocalitate.URBAN);
                } else {
                    adres.setMediuLocalitate(MediuLocalitate.RURAL);
                }
            }
//        System.out.println(adres.toString());
            return adres;


    }

    public Studii dtoToStudii(DTOAll dt){
        int codStudii = 0;
        if (dt.getCodstudii()==null||dt.getCodstudii().trim().length()==0) {
            codStudii = 0;
        } else {
            codStudii = Integer.valueOf(dt.getCodstudii());

        }
        Studii s = new Studii();
        Optional<NomenclatorStudii> ns = nomStudiiRepository.getNomStudiiByCod(codStudii);
        if (ns.isPresent()) {

            s.setNomStudii(ns.get());
            s.setDataAdd(dt.getDatast());

        }else{
            s.setNomStudii(null);
        }
        return s;


    }

    public CarteIdentitate dtoToCarteIdentitate(DTOAll dt){
        if (dt.getCiserie().trim().length() > 0
                &&dt.getCinr().trim().length() > 0) {

            CarteIdentitate ci = new CarteIdentitate();
            ci.setSerie(dt.getCiserie());
            ci.setNumar(dt.getCinr());
            ci.setDataAdd(dt.getDatast());

            return ci;


        }
        return null;
    }
    public Persoana dtoToPers(DTOAll dt) {
        Persoana p = new Persoana();
        p.setCnp(dt.getCnp());
        Nume nume=new Nume();
        nume.setNume(dt.getNume());
//        p.getNume().add(nume);
        p.addNume(nume);
        p.setPrenume(dt.getPrenume());
        p.setPrenumeMama(dt.getPrenmama());
        p.setPrenumeTata(dt.getPrentata());
        p.setLocalitateNastere(dt.getLocn());
        p.setJudetNastere(dt.getJudn());

        return p;
    }




    @Transactional
//    @ReadOnlyProperty
    public List<Persoana> getAllWithoutAnyHQL(){
        String hql = "select NEW Persoana (u.id,u.cnp,u.nume,u.prenume,u.prenumeTata,u.prenumeMama,u.localitateNastere,u.judetNastere) FROM Persoana u LEFT JOIN u.nume ";
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(hql);
        List<Persoana> users = query.getResultList();
        return users;
    }

    @Transactional
//    @ReadOnlyProperty
    public List<Persoana> getAllSimpleHQL(){
        String hql="select u from Persoana u";
        EntityManager em=entityManagerFactory.createEntityManager();
        em.clear();
        TypedQuery query=em.createQuery(hql,Persoana.class);
        query.setHint("javax.persistence.fetchgraph",em.getEntityGraph("simplu"));
        List<Persoana> users=query.getResultList();
        for(Persoana u:users){
            u.setCarteIdentitateList(new HashSet<>());
            u.setAdresaList(new HashSet<>());
            u.setStudiiList(new HashSet<>());
        }
        return users;
    }

    public List<Persoana> getAllWithoutAnyJPQL(){
        Query query = entityManagerFactory.createEntityManager().createQuery("SELECT NEW Persoana (p.cnp,p.nume,p.adresaList) FROM Persoana p LEFT JOIN p.adresaList left join p.nume");
//        query.setParameter("someValue", someValue);
        return query.getResultList();
    }



//    public List<Persoana> getAllPersWithSomeDet(){
//        List<Persoana> lista=persoanaRepository.findAll();
//        return lista.stream().map(p->{
//            return new Persoana(p.getCnp(),p.getAdresaList());
//        }).collect(Collectors.toList());
//
//    }

//@Transactional
//    public List<Persoana> listBlaze(String cnp) {
//
//      EntityManager em=entityManagerFactory.createEntityManager();
//      CriteriaBuilderFactory cbf=
//      return null;
//
//        //        EntityManager entityManager = entityManagerFactory.createEntityManager();
////
////        try {
////            // Folosiți CriteriaBuilder pentru a construi interogarea
////            List<Persoana> results = criteriaBuilderFactory.create(entityManager, Persoana.class)
////                    .where("cnp")
////                    .eq(cnp)
////                    .getResultList();
////
////            return results;
////        } finally {
////            // Închideți EntityManager pentru a elibera resursele
////            entityManager.close();
////        }
////
//    }
}

