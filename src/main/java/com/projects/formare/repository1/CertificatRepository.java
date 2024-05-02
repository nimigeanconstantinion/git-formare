package com.projects.formare.repository1;

import com.projects.formare.model.Certificat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("dataSource1")
public interface CertificatRepository extends JpaRepository<Certificat, Long> {

    @Query(value = "select c from Certificat c where c.serie=?1 and c.numar=?2")
    Optional<Certificat> getCertificatBySerieNumar(String serie, String numar);
}