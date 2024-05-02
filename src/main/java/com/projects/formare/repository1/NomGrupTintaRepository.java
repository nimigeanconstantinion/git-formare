package com.projects.formare.repository1;

import com.projects.formare.model.NomenclatorGrupTinta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NomGrupTintaRepository extends JpaRepository<NomenclatorGrupTinta,Long> {

    @Query(value = "select n from NomenclatorGrupTinta n where n.cod=?1")
    Optional<NomenclatorGrupTinta> getGrTintaByCod(int cod);
}
