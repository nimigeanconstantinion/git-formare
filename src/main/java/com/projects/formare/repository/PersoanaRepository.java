package com.projects.formare.repository;

import com.projects.formare.model.Cursant;
import com.projects.formare.model.Persoana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersoanaRepository extends JpaRepository<Persoana,Long> {

    @Query(value = "select c from Persoana c where c.cnp=?1")
    Optional<Cursant> findCursantByCursCNP(String cnp);

}
