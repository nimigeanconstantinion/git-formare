package com.projects.formare.repository2;

import com.projects.formare.dto.DTOAll;
import com.projects.formare.dto.DTOCursant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@PersistenceContext(name = "secondary",unitName = "secondary")
@Qualifier("dataSource2")
public interface DTOAllRepo extends JpaRepository<DTOAll, Integer> {
    @Query(value = "select 0 as id, * from alldt2", nativeQuery = true)
    List<Object> getAll();

    @Query(value = "select a from DTOAll a")
    List<DTOAll> mygetAll();

    @Query(value = "select d.nrc from DTOAll d group by d.nrc")
    List<Integer> getAllCursuri();

    @Query(value = "select d from DTOAll d where d.nrc=?1")
    List<DTOAll> getDTCursbyNrc(int nr);

}
