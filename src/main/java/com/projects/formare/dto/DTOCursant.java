package com.projects.formare.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


@Entity(name = "DTOCursant")
//@Table(name = "cursanti")


//@PersistenceContext(name = "secondary",unitName = "secondary")
public class DTOCursant {


    private int curs;
    private String nume;
    private String prenume;
    @Id
    private String cnp;
    private String ci_serie;
    private String ci_nr;
    private String an_n;
    private String lu_n;
    private String zi_n;
    private String loc_n;
    private String jud_n;
    private String pren_tata;
    private String pren_mama;
    private String jud;
    private String loc_sup;
    private String loc_inf;
    private String strada;
    private String nrstr;
    private String bloc;
    private String scara;
    private String et;
    private String ap;
    private String tel;
    private Date data1;
    private Date data2;
    private Date data_ex;
    private String den_progr;
    private int durata_ore;
    private String organizat;
    private String loc_sediu;
    private String jud_sediu;
    private String nr_aut;
    private Date data_aut;
    private String an_ex;
    private String lu_ex;
    private String zi_ex;
    private int medie_gen;
    private int medie_ex;
    private String calificare;
    private String serie_Cert;
    private String nr_cert;
    private String nr_contr;
    private Date data_contr;
    private int tarif;
    private String ch1;
    private Date dat1;
    private int sum1;
    private String ch2;
    private Date dat2;
    private int sum2;
    private String ch3;
    private Date dat3;
    private int sum3;
    private String ch4;
    private Date dat4;
    private int sum4;
    private int rest;
    private String finalizat;
    private String studii;
    private String profesia;
    private String locdemunca;
    private String nr_matric;
    private String obs;
    private String nrsomaj;
    private Date start_som;
    private Date sf_som;
    private Date data_abs;
    private Integer sld;
    private String gr_tinta;
    private String cod_studii;
    private String cod_stare;
    private String urb_rur;
    private int cod_prof;
    private String mot_neabs;
    private Date data_stare;
    private String agent;
    private String cf_agent;
    private String ocupatie;
    private String tip_dos;
    private int l_ang;
    private int an_ang;
}