package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "Studii")
@Table(name = "studii")
public class Studii {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studii_generator")
    @SequenceGenerator(name = "studii_generator", allocationSize = 1)
    private long id;

    @ManyToOne
    private Persoana persoana;


    @ManyToOne
    private NomenclatorStudii nomStudii;

    private Date dataAdd;

    public Studii(Studii s) {
        this.id = s.getId();
        this.nomStudii = s.getNomStudii();
        this.dataAdd = s.getDataAdd();
    }

}