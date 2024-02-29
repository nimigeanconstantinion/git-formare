package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Localitate")
@Table(name = "localitate",indexes = @Index(columnList ="siruta"))
public class Localitate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "siruta_generator")
    @SequenceGenerator(name = "siruta_generator",allocationSize = 1)
    private long id;

    private double siruta;
    private double sirutasup;
    private int tip;
    private String denumireLocalitate;
    private int judet;
}
