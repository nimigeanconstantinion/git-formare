package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "NomStudii")
@Table(name = "nom_studii")
public class NomenclatorStudii {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_nomstudii")
    @SequenceGenerator(name = "gen_nomstudii", allocationSize = 1)
    private long id;

    private int codStudii;
    private Isced isced;
    private String denumire;
    private boolean active;
    private Date dataActivarii;
    private Date dataModificarii;

    public boolean equals(Object n) {
         NomenclatorStudii newn=(NomenclatorStudii) n;
         return this.codStudii==((NomenclatorStudii) n).codStudii;
    }

}
