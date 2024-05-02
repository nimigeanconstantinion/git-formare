package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "simplu",
                attributeNodes = {
                @NamedAttributeNode("nume")
        }
        )

})



//@Builder
@Entity(name = "Persoana")
@Table(name = "persoana", indexes = @Index(columnList = "cnp,id"))
public class Persoana implements Cloneable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pers_generator")
    @SequenceGenerator(name = "pers_generator", allocationSize = 1)
    private long id;

    private String cnp;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Nume> nume = new HashSet<>();

    private String prenume;

    private String prenumeTata;
    private String prenumeMama;

    private String localitateNastere;
    private String judetNastere;
    private String taraNastere;

    @OneToMany(mappedBy = "persoana",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
//    @Builder.Default
//    @JsonIgnoreProperties
    private Set<CarteIdentitate> carteIdentitateList = new HashSet<>();


    @OneToMany(mappedBy = "persoana",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
//    @Builder.Default
//    @JsonIgnoreProperties
    private Set<Adresa> adresaList = new HashSet<>();

    @OneToMany(mappedBy = "persoana",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
//    @Builder.Default
//    @JsonIgnoreProperties
    private Set<Studii> studiiList = new HashSet<>();



    public Persoana(String cnp,Nume nume,Adresa adresa) {
//        this.id=id;
        this.cnp = cnp;
        this.adresaList.add(adresa);
//        if(this.adresaList instanceof Set<Adresa>){
//            this.adresaList.add(adresa);
//        }else {
//            this.adresaList = new HashSet<>();
//        }
        this.nume.add(nume);
//        this.prenume=prenume;
        this.studiiList=new HashSet<>();
        this.carteIdentitateList=new HashSet<>();
    }

    public Persoana(String cnp,Set<Nume> numes,Set<Adresa> adrese) {
        this.cnp = cnp;
        this.adresaList=adrese;

        this.nume=numes;
        this.studiiList=new HashSet<>();
        this.carteIdentitateList=new HashSet<>();
    }

    public Persoana(Long id,String cnp,Set<Nume> nume,String prenume,String prenumeTata,String prenumeMama,String localitateNastere,String judetNastere){
        this.id=id;
        this.cnp=cnp;
        this.nume=nume;
        this.prenume=prenume;
        this.prenumeTata=prenumeTata;
        this.prenumeMama=prenumeMama;
        this.localitateNastere=localitateNastere;
        this.judetNastere=judetNastere;
////       this.taraNastere=taraNastere;
        this.carteIdentitateList=new HashSet<>();
        this.studiiList=new HashSet<>();
        this.adresaList=new HashSet<>();
    }

    public Persoana(Long id,String cnp,Nume nume,String prenume,String prenumeTata,String prenumeMama,String localitateNastere,String judetNastere){
        this.id=id;
        this.cnp=cnp;
        this.nume.add(nume);
        this.prenume=prenume;
        this.prenumeTata=prenumeTata;
        this.prenumeMama=prenumeMama;
        this.localitateNastere=localitateNastere;
        this.judetNastere=judetNastere;
////       this.taraNastere=taraNastere;
        this.carteIdentitateList=new HashSet<>();
        this.studiiList=new HashSet<>();
        this.adresaList=new HashSet<>();
    }

    public Persoana(Persoana p) {
        this.id = p.getId();
        this.cnp = p.getCnp();
        this.nume = p.getNume();
        this.prenume = p.getPrenume();

        this.prenumeTata = p.getPrenumeTata();
        this.prenumeMama = p.getPrenumeMama();
        this.localitateNastere = p.getLocalitateNastere();
        this.judetNastere = p.getJudetNastere();
        this.taraNastere = p.getTaraNastere();
    }




    public void reset() {
        this.cnp = "";
        this.adresaList = new HashSet<>();
        this.judetNastere = "";
        this.nume = new HashSet<>();
        this.localitateNastere = "";
        this.prenume = "";
        this.studiiList = new HashSet<>();
        this.prenumeMama = "";
        this.prenumeTata = "";
        this.taraNastere = "";
        this.adresaList = new HashSet<>();

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addNume(Nume num) {

        this.nume.add(num);
    }

    public void addAdresa(Adresa adr) {
        adr.setPersoana(this);
        this.adresaList.add(adr);
    }

    public void addStud(Studii st) {
        st.setPersoana(this);
        this.studiiList.add(st);
    }

    public void addCI(CarteIdentitate ci) {
        ci.setPersoana(this);
        this.carteIdentitateList.add(ci);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persoana persoana)) return false;
        return Objects.equals(getCnp(), persoana.getCnp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnp());
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", cnp='" + cnp + '\'' +
                ", nume=" + nume.stream().findFirst().toString() +
                ", prenume='" + prenume + '\'' +
                ", prenumeTata='" + prenumeTata + '\'' +
                ", prenumeMama='" + prenumeMama + '\'' +
                ", localitateNastere='" + localitateNastere + '\'' +
                ", judetNastere='" + judetNastere + '\'' +
                ", taraNastere='" + taraNastere + '\'' +
                ", carteIdentitateList=[]"+
                ", adresaList=[],"+
                ", studiiList=[]"+
                '}';
    }
}
