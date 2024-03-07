package com.projects.formare.repository1;

import com.projects.formare.model.Nomenclator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Qualifier(value = "primaryEntityManagerFactory")
@Qualifier("dataSource1")

public interface NomenclatorRepository extends JpaRepository<Nomenclator, Long> {
    @Query(value = "select n from Nomenclator n where trim(n.cod)=?1")
    Optional<Nomenclator> findByCodC(String cod);

    @Query(value = "select n from Nomenclator n where trim(n.denumire)=?1")
    List<Nomenclator> findByName(String den);

    @Query(value = "select n from Nomenclator n where trim(n.denumire)=?1 and trim(n.cod)=''")
    List<Nomenclator> findByCompCom(String den);


}
