package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "NomenclatorStari")

@Table(name = "nomenclator_stari")

public class NomenclatorStari {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_nom_stari")
    @SequenceGenerator(name = "gen_nom_stari", initialValue = 1)
    private long id;

    private int codStare;
    private String denumire;

}