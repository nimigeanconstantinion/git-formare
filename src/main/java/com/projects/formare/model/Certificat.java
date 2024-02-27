package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Certificat")
@Table(name = "certificate")
public class Certificat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "gen_certificat")
    @SequenceGenerator(name = "gen_cert",allocationSize = 1)
    private long id;

    private String serie;
    private String numar;

    private String nrDocIntrare;
    private Date dataDocIntrare;

    private StareCertificat stareCertificat;
    private Date dataStare;



}
