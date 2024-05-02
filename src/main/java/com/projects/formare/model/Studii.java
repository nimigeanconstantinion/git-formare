package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "Studii")
@Table(name = "studii",indexes = @Index(columnList = "id"))
public class Studii {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studii_generator")
    @SequenceGenerator(name = "studii_generator", allocationSize = 1)
    private long id;

    @JoinColumn(
            name = "persoana_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Persoana persoana;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nom_studii_id")
    private NomenclatorStudii nomStudii;

    private Date dataAdd;

    public Studii(Studii s) {
        this.id = s.getId();
        this.nomStudii = s.getNomStudii();
        this.dataAdd = s.getDataAdd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studii studii)) return false;
        return getNomStudii()!=null
                &&Objects.equals(getPersoana().getCnp(), studii.getPersoana().getCnp())
                && Objects.equals(getNomStudii().getCodStudii(), studii.getNomStudii().getCodStudii());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersoana().getCnp(), getNomStudii());
    }

    @Override
    public String toString() {
        return "Studii{" +
                "id=" + id +
                ", persoana=" + persoana.getCnp() +
                ", nomStudii=" + (nomStudii!=null?nomStudii.getDenumire():"null") +
                ", dataAdd=" + dataAdd +
                '}';
    }
}