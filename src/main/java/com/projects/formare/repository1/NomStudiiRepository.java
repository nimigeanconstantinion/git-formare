package com.projects.formare.repository1;

import com.projects.formare.model.NomenclatorStudii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NomStudiiRepository extends JpaRepository<NomenclatorStudii,Long> {
    @Query(value = "select n from NomStudii n where n.codStudii=?1")
    Optional<NomenclatorStudii> getNomStudiiByCod(int cod);

}
