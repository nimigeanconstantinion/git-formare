package com.projects.formare.repository1;

import com.projects.formare.model.Adresa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dataSource1")
public interface AdresaRepository extends JpaRepository<Adresa, Long> {


    @Query(value = "select a from Adresa a left join fetch a.localitate where a.persoana.cnp=?1")
    List<Adresa> findAdresaByCNP(String cnp);
}