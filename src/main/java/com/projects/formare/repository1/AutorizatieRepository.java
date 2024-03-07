package com.projects.formare.repository1;

import com.projects.formare.model.Autorizatie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@Qualifier("dataSource1")
public interface AutorizatieRepository extends JpaRepository<Autorizatie, Long> {

    @Query(value = "select a from Autorizatie  a where a.nrAutorizatie=?1 and a.dataAutorizatie=?2")
    Optional<Autorizatie> findByNrandData(String nr, Date data);
}