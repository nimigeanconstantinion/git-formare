package com.projects.formare.repository;


import com.projects.formare.model.Curs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursRepository extends JpaRepository<Curs,Long> {
}