package com.projects.formare.repository1;

import com.projects.formare.model.Localitate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Qualifier(value = "primaryEntityManagerFactory")
@Qualifier("dataSource1")

public interface LocalitateRepository extends JpaRepository<Localitate,Long> {
}
