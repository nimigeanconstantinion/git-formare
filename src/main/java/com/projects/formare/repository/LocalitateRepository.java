package com.projects.formare.repository;

import com.projects.formare.model.Localitate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalitateRepository extends JpaRepository<Localitate,Long> {
}
