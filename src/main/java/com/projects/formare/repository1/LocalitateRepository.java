package com.projects.formare.repository1;

import com.projects.formare.model.Localitate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Qualifier(value = "primaryEntityManagerFactory")
@Qualifier("dataSource1")

public interface LocalitateRepository extends JpaRepository<Localitate, Long> {

    @Query(value = "select e from Localitate e where e.denumireLocalitate=?1")
    List<Localitate> fincByDen(String loca);

    @Query(value = "select l from Localitate l where l.denumireLocalitate=?1 " +
            "and (l.denumireLocalitate.contains'JUDEȚUl%' and l.denumireLocalitate like '%?2%')")
    Optional<Localitate> findByLocalitateandJudet(String loca, String judet);
}
