package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Cursant")
@Table(name = "cursanti")
public class Cursant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cursant_generator")
    @SequenceGenerator(name = "cursant_generator", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Persoana persoana;

    @ManyToOne(fetch = FetchType.EAGER)
    private Curs curs;

    private String nrContract;
    private Date dataContract;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PerioadaSomaj> perioadaSomajList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StareCursant> stareCursantList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Certificat> certificatList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CursantGrupTinta> cursantGrupTintaList;

    private float mediaCurs;
    private float medieExamenAbs;


}
