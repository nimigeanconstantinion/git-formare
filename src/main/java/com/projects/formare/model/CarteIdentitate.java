package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "CarteIdentitate")
@Table(name = "carte_identitate", indexes = @Index(columnList = "id"))

public class CarteIdentitate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "carteidentitate_gen")
    @SequenceGenerator(name = "carteidentitate_gen", allocationSize = 1)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persoana_id")
    @JsonIgnore
    private Persoana persoana;


    private String serie;
    private String numar;
    private Date dataAdd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarteIdentitate that)) return false;
        return Objects.equals(getPersoana().getCnp(), that.getPersoana().getCnp())
                && Objects.equals(getSerie(), that.getSerie())
                && Objects.equals(getNumar(), that.getNumar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersoana().getCnp(), getSerie(), getNumar());
    }

    @Override
    public String toString() {
        return "CarteIdentitate{" +
                "id=" + id +
                ", persoana=" + persoana +
                ", serie='" + serie + '\'' +
                ", numar='" + numar + '\'' +
                ", dataAdd=" + dataAdd +
                '}';
    }
}