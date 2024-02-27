package com.projects.formare.repository1;

import com.projects.formare.model.Cursant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("dataSource1")
public interface CursantRepository extends JpaRepository<Cursant,Long> {
    
}