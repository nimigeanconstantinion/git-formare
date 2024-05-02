package com.projects.formare.repository1;

import com.projects.formare.model.Cursant;
import com.projects.formare.model.Persoana;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//@Qualifier(value = "primaryEntityManagerFactory")
//@Qualifier("dataSource1")

@Repository
@Qualifier("dataSource1")
public interface PersoanaRepository extends JpaRepository<Persoana, Long> {

    @Transactional
    @Query(value = "select distinct c from Persoana c left join fetch c.nume " +
            "left join fetch c.adresaList adr left join fetch adr.localitate " +
            "left join fetch c.studiiList st left join fetch st.nomStudii " +
            "left join fetch c.carteIdentitateList where c.cnp=?1")
    Optional<Persoana> findPersoanaByCNP(String cnp);


    @Query(value = "select p from Persoana p")
    List<Persoana> myFindAll(Pageable pageable);

    @Query(value = "select p from Persoana p left join fetch p.adresaList left join p.nume")
    List<Persoana> findWithAddressAndNAme();

}
