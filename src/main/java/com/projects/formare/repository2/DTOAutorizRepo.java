package com.projects.formare.repository2;

import com.projects.formare.dto.DTAutorizatie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("dataSource2")
public interface DTOAutorizRepo extends JpaRepository<DTAutorizatie, Long> {

    @Query(value = "select a from DTAutorizatie a where a.NR=?1")
    Optional<DTAutorizatie> getByNr(String nraut);


}