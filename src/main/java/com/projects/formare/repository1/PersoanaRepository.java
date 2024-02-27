package com.projects.formare.repository1;

import com.projects.formare.model.Cursant;
import com.projects.formare.model.Persoana;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//@Qualifier(value = "primaryEntityManagerFactory")
//@Qualifier("dataSource1")

@Repository
@Qualifier("dataSource1")
public interface PersoanaRepository extends JpaRepository<Persoana,Long> {

    @Query(value = "select c from Persoana c where c.cnp=?1")
    Optional<Cursant> findCursantByCursCNP(String cnp);

}
