package com.projects.formare.repository1;


import com.projects.formare.model.Curs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("dataSource1")
//@PersistenceContext(unitName = "primary")
//@Qualifier("dataSource1")

public interface CursRepository extends JpaRepository<Curs, Long> {

    @Query(value = "select c from Curs c where c.nrCurs=?1")
    Optional<Curs> findCursByNrCurs(int nrc);
}