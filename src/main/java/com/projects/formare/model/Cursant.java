package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Cursant")
@Table(name = "cursant",indexes = @Index(columnList = "curs"))
public class Cursant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "cursant_generator")
    @SequenceGenerator(name = "cursant_generator",allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Persoana persoana;

    @ManyToOne(fetch = FetchType.EAGER)
    private Curs curs;

    private String nrContract;
    private LocalDateTime dataContract;

    @OneToMany
    private List<PerioadaSomaj> perioadaSomajList;

    @OneToMany
    private List<StareCursant> stareCursantList;

    private float mediaCurs;
    private float medieExamenAbs;




}
