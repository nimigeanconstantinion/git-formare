package com.projects.formare.repository1;

import com.projects.formare.model.Furnizor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dataSource")
public interface FurnizorRepository extends JpaRepository<Furnizor, Long> {

    @Query(value = "select a from Furnizor a where a.denumire like %?1% order by a.denumire asc")
    List<Furnizor> getFurnizByPartDen(String den);


}