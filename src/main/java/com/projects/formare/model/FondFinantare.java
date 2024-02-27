package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "Finantare")
@Table(name = "finantare")
public class FondFinantare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "gen_fondfinantare")
    @SequenceGenerator(name = "gen_fondfinantare",sequenceName = "gen_fondfinantare",initialValue = 1)
    private long id;

    private String denumire;

    private String codSMIS;

    private float fn;
    private float fe;


}
