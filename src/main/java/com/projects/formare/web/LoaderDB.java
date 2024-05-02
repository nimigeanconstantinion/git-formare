package com.projects.formare.web;

import com.projects.formare.dto.DTAutorizatie;
import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
//import com.projects.formare.repository2.DTOCursantRepo;
import com.projects.formare.repository2.DTOAllRepo;
import com.projects.formare.repository2.DTOAutorizRepo;
import com.projects.formare.services.CursServices;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.awt.print.Pageable;
import java.util.*;

@RestController
@RequestMapping("/api/v1/loader")
@Slf4j
public class LoaderDB {
    private NomenclatorRepository nomenclatorRepository;
    private LocalitateRepository localitateRepository;
    private CursRepository cursRepository;

    private NomStudiiRepository nomStudiiRepository;
    private DTOAllRepo dtoCursantRepo;

    private CursServices cursServices;

    private PersoanaRepository persoanaRepository;

    private DTOAutorizRepo dtoAutorizRepo;
    private FurnizorRepository furnizorRepository;

    private UserDetailRepository userDetailRepository;
    private UserRepository userRepository;

    public LoaderDB(LocalitateRepository localitateRepository,
                    NomenclatorRepository nomenclatorRepository,
                    CursRepository cursRepository,
                    DTOAllRepo dtoAllRepo,
                    PersoanaRepository persoanaRepository,
                    CursServices cursServices,
                    NomStudiiRepository nomStudiiRepository,
                    DTOAutorizRepo dtoAutorizRepo,
                    FurnizorRepository furnizorRepository,
                    UserRepository userRepository,
                    UserDetailRepository userDetailRepository) {
        this.nomenclatorRepository = nomenclatorRepository;
        this.localitateRepository = localitateRepository;
        this.cursRepository = cursRepository;
        this.dtoCursantRepo = dtoAllRepo;
        this.persoanaRepository = persoanaRepository;
        this.cursServices = cursServices;
        this.nomStudiiRepository = nomStudiiRepository;
        this.dtoAutorizRepo = dtoAutorizRepo;
        this.furnizorRepository = furnizorRepository;
        this.userRepository=userRepository;
        this.userDetailRepository=userDetailRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/nomenclator/addbulk")
    public ResponseEntity<List<Nomenclator>> addBulkNomenclator(@RequestBody List<Nomenclator> nom) {
        try {
            nomenclatorRepository.saveAll(nom);
            return ResponseEntity.ok(nom);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nomenclator")
    public ResponseEntity<List<Nomenclator>> getNomenclator() {
        try {
            return ResponseEntity.ok(nomenclatorRepository.findAll());
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/siruta/addbulk")
    public ResponseEntity<List<Localitate>> addBulkLoca(@RequestBody List<Localitate> nom) {
        try {

            localitateRepository.saveAll(nom);
            return ResponseEntity.ok(nom);
        } catch (RuntimeException e) {
            throw e;
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/curs/addbulk")
    public ResponseEntity<List<Curs>> addBulkCursuri(@RequestBody List<Curs> cursList) {
        try {
            cursRepository.saveAll(cursList);
            return ResponseEntity.ok(cursRepository.findAll());
        } catch (RuntimeException e) {
            throw e;
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getdtocursanti")
//    @Qualifier(value ="secondary")
    public ResponseEntity<List<DTOAll>> getDTOCursanti() {
        try {
            return ResponseEntity.ok(dtoCursantRepo.mygetAll());
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allget")
    public ResponseEntity<List<DTOAll>> getDTOOCursanti() {
        try {
            System.out.println("-----------------------------------------");
            return ResponseEntity.ok(dtoCursantRepo.findAll());
        } catch (RuntimeException e) {
            throw e;
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/addpers/{st}/{fin}")
    public ResponseEntity<List<Persoana>> getAllPers(@PathVariable int st, @PathVariable int fin) throws RuntimeException, CloneNotSupportedException {
        try {

            cursServices.addPersoane(st, fin);

            //            Persoana p=new Persoana("2760530335007",List.of("Nimigean"),"Paula","Paul","Mariana");

            System.out.println(persoanaRepository.findAll().size());
            return ResponseEntity.ok(cursServices.getAllWithoutAnyHQL());


            //            cursServices.addPersoane();
            //            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw e;
        }


    }

    //
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getlistpersoane/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Persoana>> getAllPerson(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return ResponseEntity.ok(persoanaRepository.myFindAll(pageable));

    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getpersbycnp/{cnp}")
    public ResponseEntity<Persoana> getPersonByCnp(@PathVariable String cnp) {

        return ResponseEntity.ok(persoanaRepository.findPersoanaByCNP(cnp).get());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getallpers")
    public ResponseEntity<List<Persoana>> getAllPersons() {

        return ResponseEntity.ok(persoanaRepository.findAll());

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addpersoana")
    public ResponseEntity<Persoana> addOnePers(@RequestBody Persoana p) {

        persoanaRepository.saveAndFlush(p);

        return ResponseEntity.ok(persoanaRepository.findPersoanaByCNP(p.getCnp()).get());


    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addnomstudii")
    public ResponseEntity<List<NomenclatorStudii>> addNomStudii(@RequestBody List<NomenclatorStudii> nom) {
        try {
            System.out.println("-----------------------------------------");
            nomStudiiRepository.saveAll(nom);
            return ResponseEntity.ok(nomStudiiRepository.findAll());
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addsiruta")
    public ResponseEntity<List<Localitate>> addSiruta(@RequestBody List<Localitate> sir) {
        try {
            System.out.println("-----------------------------------------");
            // int index=0;

            sir.stream().forEachOrdered(element -> {
                int index = sir.indexOf(element);
                //                System.out.println("Index="+index);
                localitateRepository.saveAndFlush(element);
                // ... procesați elementul cu `index`
            });
            //            return mapper.apply(index, element);
            //)
            //        ToDouble(DoubleStream.range(0, sir.size())::getAsInt)
            //                    (s->{
            //                localitateRepository.saveAndFlush(s);
            //                System.out.println(index);
            //                index++;
            //                return s;
            //            });
            //            localitateRepository.saveAllAndFlush(sir);
            return ResponseEntity.ok(localitateRepository.findAll());
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getlocas/{denLoc}/{jud}")
    public ResponseEntity<List<Localitate>> getAllLocas(@PathVariable String denLoc, @PathVariable String jud) {

        return ResponseEntity.ok(localitateRepository.findAllByLocaJud(denLoc, jud));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getcodjud/{jud}")
    public ResponseEntity<Integer> getCodJud(@PathVariable String jud) {

        return ResponseEntity.ok(localitateRepository.findJud(jud));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getlocdencodj/{jud}/{loca}")
    public ResponseEntity<List<Localitate>> getAllLocasCodJud(@PathVariable int jud, @PathVariable String loca) {


        System.out.println("jud=" + jud + " loca=" + loca);
        return ResponseEntity.ok(localitateRepository.findAllByLocaCodJud(jud, loca));

    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getdtautoriz")
    public ResponseEntity<List<DTAutorizatie>> getAllDTAutoriz() {


        return ResponseEntity.ok(dtoAutorizRepo.findAll());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getfurn/{den}")
    public ResponseEntity<List<Furnizor>> getFurn(@PathVariable String den) {

        return ResponseEntity.ok(furnizorRepository.getFurnizByPartDen(den));
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findallpers")
    public ResponseEntity<List<Persoana>> findAllPersons() {
        System.out.println("Lista are lungimea:="+cursServices.getAllWithoutAnyHQL().size());
        return ResponseEntity.ok(cursServices.getAllSimpleHQL());
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/blazep/{cnp}")
//    public ResponseEntity<List<Persoana>> findAllPersonsBlaze(@PathVariable String cnp) {
//
//        return ResponseEntity.ok(cursServices.listBlaze(cnp));
//    }

}
