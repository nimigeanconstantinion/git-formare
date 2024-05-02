package com.projects.formare.repository1;

import com.projects.formare.model.Adresa;
import com.projects.formare.model.CarteIdentitate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteIdentitateRepository extends JpaRepository<CarteIdentitate, Long> {

    @Query(value = "select c from CarteIdentitate c where trim(c.serie)=trim(?1) and trim(c.numar)=trim(?2)")
    Optional<CarteIdentitate> getCIBySerieNumar(String serie, String numar);

    @Query(value = "select a from CarteIdentitate a where a.persoana.cnp=?1")
    List<CarteIdentitate> findCarteIdentitateByCNP(String cnp);

}