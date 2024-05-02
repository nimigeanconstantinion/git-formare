package com.projects.formare.repository1;

import com.projects.formare.model.NomenclatorStari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NomenclatorStariRepository extends JpaRepository<NomenclatorStari, Long> {


    @Query(value = "select sr from NomenclatorStari sr where sr.codStare=?1")
    Optional<NomenclatorStari> getStareByCod(int cod);

}