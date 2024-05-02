package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Curs")
@Table(name = "curs", indexes = {
        @Index(columnList = "nrCurs,id", unique = true)})
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
    private String codFiscalOrg;
    private TipFurnizor tipFurnizor;


    private TipCurs tipCurs;

    private String nrDispozitie;
    private Date dataDisp;

    public void addCursant(Cursant c) {

        this.cursantList.add(c);
    }

    public void removeCursant(Cursant c) {

        this.cursantList.remove(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curs curs)) return false;
        return getNrCurs() == curs.getNrCurs() && Objects.equals(getDataStart(), curs.getDataStart()) && Objects.equals(getNomenclator().getDenumire(), curs.getNomenclator().getDenumire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNrCurs(), getDataStart(), getNomenclator().getDenumire());
    }

    @Override
    public String toString() {
        return "Curs{" +
                "nrCurs=" + nrCurs +
                ", dataStart=" + dataStart +
                ", dataSfarsit=" + dataSfarsit +
                ", dataExamen=" + dataExamen +
                ", furnizor=" + furnizor!=null?furnizor.getDenumire():"null" +
                ", finantare=" + finantare!=null ?finantare.getDenumire():"null"+
                ", oreTotal=" + oreTotal +
                ", oreTeorie=" + oreTeorie +
                ", orePractica=" + orePractica +
                ", tipCertificat=" + tipCertificat.name() +
                ", responsabil='" + responsabil + '\'' +
                ", organizator='" + organizator + '\'' +
                ", nrDispozitie='" + nrDispozitie + '\'' +
                ", dataDisp=" + dataDisp +
                '}';
    }
}
