package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Nume")
@Table(name = "nume_persoane",indexes = @Index(columnList = "id"))
public class Nume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nume_generator")
    @SequenceGenerator(name = "nume_generator", initialValue = 1, allocationSize = 1)
    private Long id;

    private String nume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nume nume1)) return false;
        return Objects.equals(getNume(), nume1.getNume());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getNume());
    }

    @Override
    public String toString() {
        return "Nume{" +
                "id=" + (id==null?"null":id) +
                ", nume='" + nume + '\'' +
                '}';
    }
}
