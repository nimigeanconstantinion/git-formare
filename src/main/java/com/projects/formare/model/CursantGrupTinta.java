package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "CursantGrupTinta")
@Table(name = "cursantgruptinta")

public class CursantGrupTinta {
    @Id
    private long id;

    @ManyToOne
    private Cursant cursant;

    @OneToOne
    private NomenclatorGrupTinta grupTinta;

    private Date dataAdd;
}
