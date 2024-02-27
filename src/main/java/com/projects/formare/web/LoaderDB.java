package com.projects.formare.web;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.*;
import com.projects.formare.repository1.*;
//import com.projects.formare.repository2.DTOCursantRepo;
import com.projects.formare.repository2.DTOAllRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loader")
@Slf4j
public class LoaderDB {
    private NomenclatorRepository nomenclatorRepository;
    private LocalitateRepository localitateRepository;
    private CursRepository cursRepository;

    private DTOAllRepo dtoCursantRepo;

    public LoaderDB(LocalitateRepository localitateRepository, NomenclatorRepository nomenclatorRepository, CursRepository cursRepository, DTOAllRepo dtoAllRepo){
        this.nomenclatorRepository=nomenclatorRepository;
        this.localitateRepository=localitateRepository;
        this.cursRepository=cursRepository;
        this.dtoCursantRepo=dtoAllRepo;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/nomenclator/addbulk")
    public ResponseEntity<List<Nomenclator>> addBulkNomenclator(@RequestBody List<Nomenclator> nom){
        try{
            nomenclatorRepository.saveAll(nom);
            return ResponseEntity.ok(nom);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nomenclator")
    public ResponseEntity<List<Nomenclator>> getNomenclator(){
        try{
            return ResponseEntity.ok(nomenclatorRepository.findAll());
        }catch (RuntimeException e){
            throw e;
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/siruta/addbulk")
    public ResponseEntity<List<Localitate>> addBulkLoca(@RequestBody List<Localitate> nom){
        try{

            localitateRepository.saveAll(nom);
            return ResponseEntity.ok(nom);
        }catch (RuntimeException e){
            throw e;
        }
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/curs/addbulk")
    public ResponseEntity<List<Curs>> addBulkCursuri(@RequestBody List<Curs> cursList){
        try {
            cursRepository.saveAll(cursList);
            return ResponseEntity.ok(cursRepository.findAll());
        }catch (RuntimeException e){
            throw e;
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getdtocursanti")
//    @Qualifier(value ="secondary")
    public ResponseEntity<List<DTOAll>> getDTOCursanti(){
        try{
            System.out.println("-----------------------------------------");
            return ResponseEntity.ok(dtoCursantRepo.mygetAll());
        }catch (RuntimeException e){
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allget")
  public ResponseEntity<List<DTOAll>> getDTOOCursanti(){
        try{
            System.out.println("-----------------------------------------");
            return ResponseEntity.ok(dtoCursantRepo.findAll());
        }catch (RuntimeException e){
            throw e;
        }
    }

}
