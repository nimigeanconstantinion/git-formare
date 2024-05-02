package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "StareCursant")
@Table(name = "starecursant")
public class StareCursant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "starecursant_gen")
    @SequenceGenerator(name = "starecursant_gen", allocationSize = 1)
    private long id;

    @ManyToOne
    private NomenclatorStari stare;

    private Date dataStare;

    private TipCursant tipCursant;


    private String denumireAngajator;
    private String codUnicInregistrare;

    private int lunaAng = 0;
    private int anulAng = 0;

    @ManyToOne
    private MotivNeabsolvire motivNeabsolvire;
}
