package com.projects.formare.repository1;

import com.projects.formare.model.Adresa;
import com.projects.formare.model.Studii;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dataSource1")
public interface StudiiRepository extends JpaRepository<Studii,Long> {

    @Query(value = "select s from Studii s where s.persoana.cnp=?1")
    List<Studii> findStudiiByCNP(String cnp);
}