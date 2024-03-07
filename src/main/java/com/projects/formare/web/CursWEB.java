package com.projects.formare.web;


import com.projects.formare.dto.DTOAll;
import com.projects.formare.model.Curs;
import com.projects.formare.repository1.CursRepository;
import com.projects.formare.repository2.DTOAllRepo;
import com.projects.formare.services.AddCursService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/curs")
@Slf4j
public class CursWEB {
    private AddCursService addCursService;
    private CursRepository cursRepository;

    private DTOAllRepo dtoAllRepo;

    public CursWEB(AddCursService addCursService,
                   CursRepository cursRepository,
                   DTOAllRepo dtoAllRepo) {
        this.addCursService = addCursService;
        this.cursRepository = cursRepository;
        this.dtoAllRepo = dtoAllRepo;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/bulkaddcurs")
    public ResponseEntity<List<Curs>> bulkAddCursuri() {
        System.out.println("Flag 0");
        addCursService.addCursuri();
        return ResponseEntity.ok(cursRepository.findAll());

    }
}