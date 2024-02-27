package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "GrupTinta")
@Table(name = "nom_gruptinta")

public class NomenclatorGrupTinta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "gen_gruptinta")
    @SequenceGenerator(name = "gen_gruptinta",sequenceName = "gen_gruptinta",initialValue = 1)
    private long id;

    private int cod;
    private String denumire;


}
