package com.projects.formare.web;

import com.projects.formare.dto.DTAutorizatie;
import com.projects.formare.model.Autorizatie;
import com.projects.formare.model.Nomenclator;
import com.projects.formare.repository1.AutorizatieRepository;
import com.projects.formare.repository1.NomenclatorRepository;
import com.projects.formare.repository2.DTOAutorizRepo;
import com.projects.formare.services.DTAutorizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/autoriz")
@Slf4j
public class AutorizWEB {

    private DTOAutorizRepo dtoAutorizRepo;
    private NomenclatorRepository nomenclatorRepository;
    private DTAutorizService dtAutorizService;
    private AutorizatieRepository autorizatieRepository;

    public AutorizWEB(DTOAutorizRepo dtoAutorizRepo
            , NomenclatorRepository nomenclatorRepository
            , DTAutorizService dtAutorizService
            , AutorizatieRepository autorizatieRepository) {
        this.nomenclatorRepository = nomenclatorRepository;
        this.dtoAutorizRepo = dtoAutorizRepo;
        this.dtAutorizService = dtAutorizService;
        this.autorizatieRepository = autorizatieRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nomenclator/cod/{cod}")

    public ResponseEntity<Nomenclator> getNomCorByCod(@PathVariable String cod) {
        try {
            Optional<Nomenclator> nom = nomenclatorRepository.findByCodC(cod);
            if (nom.isPresent()) {
                return ResponseEntity.ok(nom.get());
            } else {
                System.out.println("N-am gasit " + cod);
                throw new RuntimeException("Eroare");
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nomenclator/den/{den}")

    public ResponseEntity<Nomenclator> getNomCorByDen(@PathVariable String den) {
        try {
            Nomenclator nom = dtAutorizService.getNomCalifByCodDen("", den);
            return ResponseEntity.ok(nom);
        } catch (RuntimeException e) {
            throw e;
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/bulkadd")

    public ResponseEntity<List<Autorizatie>> bulkAddAutoriz() {
        System.out.println("------------------------in--------------------");
        try {
            List<DTAutorizatie> lista = dtoAutorizRepo.findAll();

            System.out.println("lista size="+lista.size());
                    
            for (DTAutorizatie dt : lista) {
                System.out.println(dt.getNR());
                dtAutorizService.addAut(dt);

            }
            return ResponseEntity.ok(autorizatieRepository.findAll());
        } catch (RuntimeException e) {
            throw e;
        }
//        return ResponseEntity.ok(autorizatieRepository.findAll());
    }

}