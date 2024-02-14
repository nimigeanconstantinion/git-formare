package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Persoana")
@Table(name = "persoana",indexes = @Index(columnList ="cnp"))
public class Persoana {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "pers_generator")
    @SequenceGenerator(name = "pers_generator",  allocationSize = 1)
    private long id;

    private String cnp;

    @ElementCollection
    private List<String> nume;

    private String prenume;

    private String prenumeTata;
    private String prenumeMama;

    private String localitateNastere;
    private String judetNastere;
    private String taraNastere;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Adresa> adresaList;



    public Persoana(String cnp){
        this.cnp=cnp;
    }
    @Override
    public boolean equals(Object p){
        Persoana pr=(Persoana) p;
        return pr.getCnp().equals(this.cnp);
    }

}
