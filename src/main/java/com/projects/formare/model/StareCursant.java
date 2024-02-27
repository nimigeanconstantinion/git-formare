package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "StareCursant")
@Table(name = "starecursant")
public class StareCursant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "starecursant_gen")
    @SequenceGenerator(name = "starecursant_gen",allocationSize = 1)
    private long id;

    private StarePers starePers;
    private LocalDateTime dataStare;

    @OneToOne
    private MotivNeabsolvire motivNeabsolvire;
}
