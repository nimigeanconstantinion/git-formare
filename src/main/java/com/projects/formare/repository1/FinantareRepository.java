package com.projects.formare.repository1;

import com.projects.formare.model.Finantare;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dataSource1")
public interface FinantareRepository extends JpaRepository<Finantare, Long> {

    @Query(value = "select f from Finantare f where trim(f.codScurt)=?1")
    List<Finantare> getFondFinantareByDen(String den);
}