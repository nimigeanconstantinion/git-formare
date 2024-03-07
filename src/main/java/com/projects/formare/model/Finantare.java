package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "Finantare")
@Table(name = "finantare")
public class Finantare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_fondfinantare")
    @SequenceGenerator(name = "gen_fondfinantare", sequenceName = "gen_fondfinantare", initialValue = 1)
    private long id;

    @Column(length = 20, unique = true)
    private String codScurt;

    private String denumire;

    private String codSMIS;

    private BigDecimal fn;
    private BigDecimal fe;


}
