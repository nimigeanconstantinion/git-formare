package com.projects.formare.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Autorizatie")
@Table(name = "autorizatie")

public class Autorizatie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "aut_generator")
    @SequenceGenerator(name = "aut_generator",initialValue = 1,allocationSize = 1)
    private Long id;

//    @ManyToMany
//    @JoinTable(
//            name = "aut_nomcor",
//            joinColumns = @JoinColumn(name = "autorizatie_id"),
//            inverseJoinColumns = @JoinColumn(name = "nomenclator_id")
//    )
//    private List<Nomenclator> nomenclatorList;

    private String nrAutorizatie;
    private LocalDateTime dataAutorizatie;
    private LocalDateTime dataExpirarii;

    private String nrRNFPA;
    private LocalDateTime dataRNFPA;


    private TipCurs tipCurs;

    private int totalOre,oreTeorie,orePractica;


    @ManyToOne
    private Nomenclator nomenclator;

    private String bazaLegala;
    private String condAcces;

    private String notare;
    
    private int nivel;

    @ElementCollection
    private List<String> competente;
    
}
