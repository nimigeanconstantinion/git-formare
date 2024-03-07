package com.projects.formare.repository1;

import com.projects.formare.model.Localitate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Qualifier(value = "primaryEntityManagerFactory")
@Qualifier("dataSource1")

public interface LocalitateRepository extends JpaRepository<Localitate, Long> {

    @Query(value = "select e from Localitate e where e.denumireLocalitate=?1")
    List<Localitate> findByDen(String loca);

    @Query(value = "select l from Localitate l where l.denumireLocalitate=?1 and (l.denumireLocalitate like 'JUDEȚUl%' and l.denumireLocalitate like '%?2%')")
    Optional<Localitate> findByLocalitateandJudet(String loca, String judet);


    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate),b as(\n" +
            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
            "select * from e where e.denumireLocalitate like %?1% and judet=(select judet from e where tip=40 and denumireLocalitate like %?2%);",nativeQuery = true)
    List<Localitate> findAllByLocaJud(String denLoca,String jud);

    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate where judet=?1),b as(\n" +
            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
            "select * from e where e.tip<>40 and e.denumireLocalitate like %?2%",nativeQuery = true)
    List<Localitate> findAllByLocaCodJud( int jud, String loca);

    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate where tip=40),b as(\n" +
            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
            "select judet from e where e.denumireLocalitate like %?1% ;",nativeQuery = true)
    Integer findJud(String jud);

}
