package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Curs")
@Table(name = "curs",indexes = {
        @Index(columnList = "codCurs",unique = true)})
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "curs_generator")
    @SequenceGenerator(name = "curs_generator",initialValue = 1,allocationSize = 1)
    private long id;

    @Column(name = "codCurs",unique = true)
    private int codCurs;

    private LocalDateTime data_st;
    private LocalDateTime data_sf;
    private LocalDateTime data_ex;

    private String furnizor;
    private String codFiscal;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Cursant> cursantList;

    @OneToOne(fetch = FetchType.EAGER)
    private Autorizatie autorizatie;

    @OneToOne(fetch = FetchType.EAGER)
    private Nomenclator nomenclator ;

    public void addCursant(Cursant c){

        this.cursantList.add(c);
    }

    public void removeCursant(Cursant c){

        this.cursantList.remove(c);
    }
}
