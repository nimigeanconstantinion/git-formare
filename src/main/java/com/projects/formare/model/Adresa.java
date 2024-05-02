package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.projects.formare.model.MediuLocalitate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Adresa")
@Table(name = "adresa", indexes = @Index(columnList = "id,persoana_id"))

public class Adresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adr_generator")
    @SequenceGenerator(name = "adr_generator", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persoana_id")
    @JsonBackReference
    private Persoana persoana;


    @ManyToOne(fetch = FetchType.EAGER)
    private Localitate localitate;

    private String locaInf;
    private String locaSup;
    private String judet;

    private String tara;
    private String strada;
    private String nrStrada;

    private String blCasa;
    private String scara;
    private String et;
    private String ap;
    private String tel;
    private String email;
    private Date dateAdd;
    private MediuLocalitate mediuLocalitate = MediuLocalitate.URBAN;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresa adresa)) return false;
        return Objects.equals(getPersoana().getCnp(), adresa.getPersoana().getCnp())
                && Objects.equals(getLocaInf(), adresa.getLocaInf())
                && Objects.equals(getLocaSup(), adresa.getLocaSup())
                && Objects.equals(getJudet(), adresa.getJudet())
                && Objects.equals(getStrada(), adresa.getStrada())
                && Objects.equals(getNrStrada(), adresa.getNrStrada())
                && Objects.equals(getBlCasa(),adresa.getBlCasa())
                && Objects.equals(getScara(), adresa.getScara())
                && Objects.equals(getEt(), adresa.getEt())
                && Objects.equals(getAp(), adresa.getAp())
                && getMediuLocalitate() == adresa.getMediuLocalitate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersoana().getCnp(), (getLocalitate()!=null?getLocalitate().getDenumireLocalitate():""), getLocaInf(), getLocaSup(), getJudet(), getTara(), getStrada(), getNrStrada(), getBlCasa(), getScara(), getEt(), getAp(), getMediuLocalitate());
    }

    @Override
    public String toString() {
        return "Adresa{" +
                ", locaInf='" + locaInf + '\'' +
                ", locaSup='" + locaSup + '\'' +
                ", judet='" + judet + '\'' +
                ", tara='" + tara + '\'' +
                ", strada='" + strada + '\'' +
                ", nrStrada='" + nrStrada + '\'' +
                ", blCasa='" + blCasa + '\'' +
                ", scara='" + scara + '\'' +
                ", et='" + et + '\'' +
                ", ap='" + ap + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", dateAdd=" + dateAdd +
                ", mediuLocalitate=" + mediuLocalitate.toString() +
                '}';
    }
}

