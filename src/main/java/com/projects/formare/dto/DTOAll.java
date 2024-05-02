package com.projects.formare.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


@Entity(name = "DTOAll")
@Table(name = "alldt2")

public class DTOAll implements Serializable {


    @Id
//        @GeneratedValue(strategy = GenerationType.AUTO,generator = "gen_dto")
//        @SequenceGenerator(name = "gen_dto",allocationSize = 1)
    private int id;

    private int nrc;

    @Column(length = 200)
    private String dencalif;

    @Column(length = 20)
    private String cornom;

    private Date datast;

    private Date datasf;

    private Date dataex;

    @Column(length = 30)
    private String nrautc;

    private Date dataut;

    @Column(length = 30)
    private String tip;

    private int nrore;

    private int oret;

    private int orep;

    @Column(length = 70)
    private String coord;

    @Column(length = 70)
    private String form1;

    @Column(length = 70)
    private String form2;

    @Column(length = 70)
    private String form3;

    @Column(length = 70)
    private String form4;

    @Column(length = 70)
    private String form5;

    @Column(length = 70)
    private String form6;

    @Column(length = 70)
    private String form7;

    @Column(length = 70)
    private String form8;

    @Column(length = 70)
    private String pres;

    @Column(length = 70)
    private String membr1;

    @Column(length = 70)
    private String membr2;

    @Column(length = 70)
    private String membr3;

    @Column(length = 10)
    private String nrpv;

    private Date datapv;

    @Column(length = 20)
    private String codforg;

    @Column(length = 200)
    private String org;

    @Column(length = 50)
    private String finantare;

    @Column(length = 70)
    private String responsab;

    @Column(length = 20)
    private String nrdisp;

    private Date datadisp;

    private int curs;

    @Column(length = 50)
    private String nume;

    @Column(length = 50)
    private String prenume;

    @Column(length = 15)
    private String cnp;

    @Column(length = 5)
    private String ciserie;

    @Column(length = 15)
    private String cinr;

    @Column(length = 4)
    private String ann;

    @Column(length = 2)
    private String lun;

    @Column(length = 2)
    private String zin;

    @Column(length = 50)
    private String locn;

    @Column(length = 50)
    private String judn;

    @Column(length = 50)
    private String prentata;

    @Column(length = 50)
    private String prenmama;

    @Column(length = 70)
    private String jud;

    @Column(length = 70)
    private String locsup;

    @Column(length = 70)
    private String locinf;

    @Column(length = 100)
    private String strada;

    @Column(length = 10)
    private String nrstr;

    @Column(length = 10)
    private String bloc;

    @Column(length = 10)
    private String scara;

    @Column(length = 10)
    private String et;

    @Column(length = 10)
    private String ap;

    @Column(length = 20)
    private String tel;

    private Date data1;

    private Date data2;

    private Date dataex1;

    @Column(length = 200)
    private String denprogr;

    private int durataore;

    @Column(length = 200)
    private String organizat;

    @Column(length = 100)
    private String locsediu;

    @Column(length = 100)
    private String judsediu;

    @Column(length = 20)
    private String nrautb;

    private Date dataaut;

    @Column(length = 4)
    private String anex;

    @Column(length = 2)
    private String luex;

    @Column(length = 2)
    private String ziex;

    private float mediegen;

    private float medieex;

    @Column(length = 200)
    private String calificare;

    @Column(length = 5)
    private String seriecert;

    @Column(length = 20)
    private String nrcert;

    @Column(length = 6)
    private String nrcontr;

    private Date datacontr;

    private int tarif;

    @Column(length = 6)
    private String ch1;

    private Date dat1;

    private float sum1;

    @Column(length = 6)
    private String ch2;

    private Date dat2;

    private float sum2;

    @Column(length = 6)
    private String ch3;

    private Date dat3;

    private float sum3;

    @Column(length = 10)
    private String ch4;

    private Date dat4;

    private float sum4;

    private float rest;

    @Column(length = 50)
    private String finalizat;

    @Column(length = 100)
    private String studii;

    @Column(length = 100)
    private String profesia;

    @Column(length = 100)
    private String locdemunca;

    @Column(length = 20)
    private String nrmatric;

    @Column(columnDefinition = "mediumtext")
    private String obs;

    @Column(length = 20)
    private String nrsomaj;

    private Date startsom;

    private Date sfsom;

    private Date dataabs;

    private Integer sld;

    @Column(length = 100)
    private String grtinta;

    @Column(length = 2)
    private String codstudii;

    @Column(length = 2)
    private String codstare;

    @Column(length = 2)
    private String urbrur;

    private int codprof;

    @Column(length = 2)
    private String motneabs;

    private Date datastare;

    @Column(length = 200)
    private String agent;

    @Column(length = 30)
    private String cfagent;

    @Column(length = 20)
    private String ocupatie;

    @Column(length = 20)
    private String tipdos;

    private int lang;

    private int anang;

    @Column(length = 15)
    private String cod;

    @Column(length = 100)
    private String calificar2;

    private int rnk;

    @Column(length = 2)
    private String nivel;

    @Column(length = 100)
    private String specif;

    @Column(length = 2)
    private String codgr;

    @Column(length = 100)
    private String descgr;

    @Column(length = 2)
    private String codstare2;

    @Column(length = 100)
    private String specstare;

    @Column(length = 2)
    private String codmotiv;

    @Column(length = 100)
    private String specmotiv;

    @Column(name = "_NullFlags", length = 30)
    private String nullFlags;

    @Column(name = "deletionMark", length = 20)
    private String deletionMark;

    public String toString() {
        return "Nr curs=" + this.nrc + " CodCalif=" + this.cornom + " -" + this.dencalif;

    }
}