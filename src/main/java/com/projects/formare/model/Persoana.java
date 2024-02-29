package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Persoana")
@Table(name = "persoana",indexes = @Index(columnList ="cnp"))
public class Persoana implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "pers_generator")
    @SequenceGenerator(name = "pers_generator",  allocationSize = 1)
    private long id;

    private String cnp;

    @ElementCollection
    private List<String> nume=new ArrayList<>();

    private String prenume;

    private String prenumeTata;
    private String prenumeMama;

    private String localitateNastere;
    private String judetNastere;
    private String taraNastere;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Adresa> adresaList=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Studii> studiiList=new ArrayList<>();




    public Persoana(String cnp){
        this.cnp=cnp;
    }

    public Persoana(Persoana p){
        this.id=p.getId();
        this.cnp=p.getCnp();
        this.nume=p.getNume();
        this.prenume=p.getPrenume();
        this.studiiList=p.getStudiiList();
        this.adresaList=p.getAdresaList();
        this.prenumeTata=p.getPrenumeTata();
        this.prenumeMama=p.getPrenumeMama();
        this.localitateNastere=p.getLocalitateNastere();
        this.judetNastere=p.getJudetNastere();
        this.taraNastere=p.getTaraNastere();
    }

    @Override
    public boolean equals(Object p){
        Persoana pr=(Persoana) p;
        return pr.getCnp().equals(this.cnp);
    }


    public void reset(){
        this.cnp="";
        this.adresaList=new ArrayList<>();
        this.judetNastere="";
        this.nume=new ArrayList<>();
        this.localitateNastere="";
        this.prenume="";
        this.studiiList=new ArrayList<>();
        this.prenumeMama="";
        this.prenumeTata="";
        this.taraNastere="";
        this.adresaList=new ArrayList<>();

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addNume(String num){
        this.nume.add(num);
    }
    public void addAdresa(Adresa adr){
        this.adresaList.add(adr);
    }
    public void addStud(Studii st){
        this.studiiList.add(st);
    }


}
