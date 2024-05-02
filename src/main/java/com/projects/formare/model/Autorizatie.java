package com.projects.formare.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity(name = "Autorizatie")
@Table(name = "autorizatie")

public class Autorizatie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "aut_generator")
    @SequenceGenerator(name = "aut_generator", initialValue = 1, allocationSize = 1)
    private Long id;

    //    @ManyToMany
    //    @JoinTable(
    //            name = "aut_nomcor",
    //            joinColumns = @JoinColumn(name = "autorizatie_id"),
    //            inverseJoinColumns = @JoinColumn(name = "nomenclator_id")
    //    )
    //    private List<Nomenclator> nomenclatorList;

    private String nrAutorizatie;
    private Date dataAutorizatie;
    private Date dataExpirarii;

    private String nrRNFPA;
    private Date dataRNFPA;


    private TipCurs tipCurs;

    private int totalOre, oreTeorie, orePractica;


    @ManyToOne(fetch = FetchType.EAGER)
    private Nomenclator nomenclator;

    private String bazaLegala;
    private String condAcces;

    private String notare;

    private int nivel;

    @OneToMany(mappedBy = "autorizatie",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Competenta> competente = new HashSet<>();

    private LocalDateTime dateAdd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autorizatie that)) return false;
        return Objects.equals(getNrAutorizatie(), that.getNrAutorizatie()) && Objects.equals(getDataAutorizatie(), that.getDataAutorizatie()) && Objects.equals(getNrRNFPA(), that.getNrRNFPA()) && Objects.equals(getDataRNFPA(), that.getDataRNFPA()) && getTipCurs() == that.getTipCurs() && Objects.equals(getNomenclator(), that.getNomenclator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNrAutorizatie(), getDataAutorizatie(), getNrRNFPA(), getDataRNFPA(), getTipCurs(), getNomenclator().getDenumire());
    }


    public void addCompetenta(Competenta comp) {
        comp.setAutorizatie(this);
        if (!this.getCompetente().contains(comp)) {
            this.competente.add(comp);

        }
    }

    public void delCompet(String comp) {
        if (this.competente.contains(comp)) {
            this.competente.remove(comp);
        }
    }
}
