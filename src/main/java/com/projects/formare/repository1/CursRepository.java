package com.projects.formare.repository1;


import com.projects.formare.model.Curs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("dataSource1")
//@PersistenceContext(unitName = "primary")
//@Qualifier("dataSource1")

public interface CursRepository extends JpaRepository<Curs,Long> {
}