package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "competente")
public class Competenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "competente_generator")
    @SequenceGenerator(name = "competente_generator",allocationSize = 1,sequenceName = "competente_generator")
    private Long id;

    private String denumire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Autorizatie autorizatie;

    public Competenta(Autorizatie autorizatie,String denumire){
        this.autorizatie=autorizatie;
        this.denumire=denumire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competenta that)) return false;
        return  Objects.equals(getDenumire(), that.getDenumire()) && Objects.equals(getAutorizatie().getNrAutorizatie(), that.getAutorizatie().getNrAutorizatie());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getDenumire(), getAutorizatie().getNrAutorizatie(),getAutorizatie().getDataAutorizatie());
    }

    @Override
    public String toString() {
        return "Competenta{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", autorizatie=" + (autorizatie!=null?autorizatie.getNrRNFPA()+"-"+autorizatie.getDataRNFPA():"null") +
                '}';
    }
}
