package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity(name = "Studii")
@Table(name = "studii")
public class Studii {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studii_generator")
    @SequenceGenerator(name = "studii_generator", allocationSize = 1)
    private long id;

    @ManyToOne
    private Persoana persoana;

    private Isced isced;

    @ManyToOne
    private NomenclatorStudii nomStudii;

    private LocalDateTime dataAbs;

}