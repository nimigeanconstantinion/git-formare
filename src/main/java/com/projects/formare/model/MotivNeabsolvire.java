package com.projects.formare.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Motive")
@Table(name = "nom_motneabsolvire")
public class MotivNeabsolvire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_motivneabs")
    @SequenceGenerator(name = "gen_motneabs", allocationSize = 1)
    private long id;

    private boolean activ;
    private int codMotiv;
    private String denumire;

    private TipMotivNeabsolvire tipMotivNeabsolvire;


}
