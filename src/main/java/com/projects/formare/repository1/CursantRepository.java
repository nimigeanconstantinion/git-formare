package com.projects.formare.repository1;

import com.projects.formare.model.Cursant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("dataSource1")
public interface CursantRepository extends JpaRepository<Cursant,Long> {
    @Query(value = "select c from Cursant c where c.persoana.cnp=?1 and c.curs.nrCurs=?2")
    Optional<Cursant> findCursantByCNPandNrCurs(String cnp,int nrcurs);

}