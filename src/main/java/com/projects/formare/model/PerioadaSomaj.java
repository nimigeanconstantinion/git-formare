package com.projects.formare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "PerioadaSomaj")
@Table(name = "perioadasomaj")
public class PerioadaSomaj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "person_generator")
    @SequenceGenerator(name = "person_generator",allocationSize = 1)
    private long id;

    private String nrSomaj;
    private LocalDateTime dataStartSomaj;
    private LocalDateTime dataSfSomaj;
}
