package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Cursant")
@Table(name = "cursanti")
public class Cursant implements Serializable {
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PerioadaSomaj> perioadaSomajList = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StareCursant> stareCursantList = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "cursant")
    private Set<Certificat> certificatList = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NomenclatorGrupTinta cursantGrupTintaList;

    private float mediaCurs = 0;
    private float medieExamenAbs = 0;
    private Date dataAbsolvirii;

    public void addPerSomaj(PerioadaSomaj p){
        this.getPerioadaSomajList().add(p);
    }

    public void addStareCursant(StareCursant st){
        this.getStareCursantList().add(st);
    }
}
