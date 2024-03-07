package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Curs")
@Table(name = "curs", indexes = {
        @Index(columnList = "nrCurs", unique = true)})
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "curs_generator")
    @SequenceGenerator(name = "curs_generator", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(name = "nrCurs", unique = true)
    private int nrCurs;

    private Date dataStart;
    private Date dataSfarsit;
    private Date dataExamen;

    @ManyToOne
    private Furnizor furnizor;

    @ManyToOne
    private Finantare finantare;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cursant> cursantList = new ArrayList<>();

    private String nrProcesVerbalExamen;
    private Date dataProcesVerbalExamen;

    @ManyToOne(fetch = FetchType.EAGER)
    private Autorizatie autorizatie;

    @ManyToOne(fetch = FetchType.EAGER)
    private Nomenclator nomenclator;


    private int oreTotal, oreTeorie, orePractica;

    private TipCertificat tipCertificat;
    private String responsabil;
    private String organizator;
    private TipCurs tipCurs;

    private String nrDispozitie;
    private Date dataDisp;

    public void addCursant(Cursant c) {

        this.cursantList.add(c);
    }

    public void removeCursant(Cursant c) {

        this.cursantList.remove(c);
    }
}
