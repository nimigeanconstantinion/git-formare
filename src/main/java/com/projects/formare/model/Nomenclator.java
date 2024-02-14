package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Nomenclator")
@Table(name = "nomenclator",indexes = @Index(columnList ="cod"))

public class Nomenclator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "nomcor_generator")
    @SequenceGenerator(name = "nomcor_generator",initialValue = 1,allocationSize = 1)
    private long id;

    
    private String cod;

    private String denumire;

}
