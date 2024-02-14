package com.projects.formare.repository;

import com.projects.formare.model.Nomenclator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomenclatorRepository extends JpaRepository<Nomenclator,Long> {
}
