package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Adresa")
@Table(name = "adresa")
public class Adresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adr_generator")
    @SequenceGenerator(name = "adr_generator", allocationSize = 1)
    private long id;

    @OneToOne
    private Localitate localitateInfo;
    private String localitateInf;
    private String locaSup;
    private String judet;

    private String tara;
    private String strada;
    private String nrStrada;

    private String blCasa;
    private String scara;
    private String et;
    private String ap;
    private String tel;
    private String email;
    private Date dateAdd;


}

