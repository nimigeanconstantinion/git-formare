package com.projects.formare.repository1;

import com.projects.formare.model.Certificat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("dataSource1")
public interface CertificatRepository extends JpaRepository<Certificat,Long> {
    
}