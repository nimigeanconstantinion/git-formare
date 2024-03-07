package com.projects.formare.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "DTAutorizatie")
@Table(name = "autoriz")
public class DTAutorizatie {
    @Id
    private long id;

    private String NR;
    private Date DAT_AUT;
    private String COD_NOM;
    private String COR;
    private String CALIFICARE;
    private String NR_RNFPA;
    private Date DATA_RNFPA;
    private String NRORE;
    private String ORE_TEORIE;
    private String ORE_PRACT;
    private String TIP_CERTIF;
    private String TIP;
    private String BAZA_LEG;
    private String COND_ACCES;
    private String NOTARE;
    private String COMPET1;
    private String COMPET2;
    private String COMPET3;
    private String COMPET4;
    private String COMPET5;
    private String COMPET6;
    private String COMPET7;
    private String COMPET8;
    private String COMPET9;
    private String COMPET10;
    private String COMPET11;
    private String COMPET12;
    private String COMPET13;
    private String COMPET14;
    private String COMPET15;
    private String COMPET16;
    private String COMPET17;
    private String COMPET18;
    private String COMPET19;
    private String COMPET20;
    private String COMPET21;
    private String COMPET22;
    private String COMPET23;
    private String COMPET24;
    private String COMPET25;
    private String DISC_T1;
    private String DISC_T2;
    private String DISC_T3;
    private String DISC_T4;
    private String DISC_T5;
    private String DISC_T6;
    private String DISC_T7;
    private String DISC_T8;
    private String DISC_T9;
    private String DISC_T10;
    private String DISC_T11;
    private String DISC_T12;
    private String DISC_T13;
    private String DISC_T14;
    private String DISC_T15;
    private String DISC_T16;
    private String DISC_T17;
    private String DISC_T18;
    private String DISC_T19;
    private String DISC_T20;
    private String DISC_T21;
    private String DISC_T22;
    private String DISC_T23;
    private String DISC_T24;
    private String DISC_T25;
    private String DISC_P1;
    private String DISC_P2;
    private String DISC_P3;
    private String DISC_P4;
    private String DISC_P5;
    private String DISC_P6;
    private String DISC_P7;
    private String DISC_P8;
    private String DISC_P9;
    private String DISC_P10;
    private String DISC_P11;
    private String DISC_P12;
    private String DISC_P13;
    private String DISC_P14;
    private String DISC_P15;
    private String DISC_P16;
    private String DISC_P17;
    private String DISC_P18;
    private String DISC_P19;
    private String DISC_P20;
    private String DISC_P21;
    private String DISC_P22;
    private String DISC_P23;
    private String DISC_P24;
    private String DISC_P25;
    private double RnK;
    private Object _NullFlags;
    private String deletionMark;

}