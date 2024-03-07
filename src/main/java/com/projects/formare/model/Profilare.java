package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Profilare")
@Table(name = "profilare")

public class Profilare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_nom_profilare")
    @SequenceGenerator(name = "gen_nom_profilare", allocationSize = 1)
    private long id;

    private String codProfilare;
    private String denumire;

}