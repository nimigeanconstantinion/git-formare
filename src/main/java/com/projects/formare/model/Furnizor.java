package com.projects.formare.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Furnizor")
@Table(name = "furnizor")


public class Furnizor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_furnizor")
    @SequenceGenerator(name = "gen_furniz", allocationSize = 1)
    private long id;

    private String denumire;
    private String codFiscal;

    private TipFurnizor tipFurnizor;
}
