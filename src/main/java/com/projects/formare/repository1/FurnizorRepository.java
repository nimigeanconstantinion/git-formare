package com.projects.formare.repository1;

import com.projects.formare.model.Furnizor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("dataSource")
public interface FurnizorRepository extends JpaRepository<Furnizor,Long> {
    
}