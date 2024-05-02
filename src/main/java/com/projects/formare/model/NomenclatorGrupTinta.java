package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "NomenclatorGrupTinta")
@Table(name = "nom_gruptinta")

public class NomenclatorGrupTinta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "gen_gruptinta")
    @SequenceGenerator(name = "gen_gruptinta",sequenceName = "gen_gruptinta",allocationSize = 1)
    private long id;

    private int cod;
    private String denumire;

    private boolean activ;
    private Date dataActivarii;


}
