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
//
//    @Query(value = "select e from Localitate e where e.denumireLocalitate=?1")
//    List<Localitate> findByDen(String loca);
//    SELECT * FROM sheet1 WHERE SIRUTASUP<>1 AND sirutasup NOT in(SELECT siruta FROM sheet1 WHERE sirutasup=1) AND JUDET IN(SELECT JUDET FROM SHEET1 AS X WHERE X.SIRUTASUP=1 AND X.DENUMIRELO="JUDETUL SUCEAVA")
//
//    @Query(value = "select l from Localitate l where l.sirutasup<>1 and l.sirutasup not " +
//                   "in(select p.siruta from Localitate p where p.sirutasup=1) and l.denumireLocalitate=?1 and l.judet " +
//                   "in(select x.judet from Localitate x where x.denumireLocalitate like %?2% and x.sirutasup=1)")
//    Optional<Localitate> findByLocalitateandJudet(String loca, String judet);


//    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate),b as(\n" +
//            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
//            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
//            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
//            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
//            "select * from localitate as e where e.denumireLocalitate like %?1% and e.sirutasup<>1 and e.sirutasup not " +
//            "in(in(select p.siruta from localitate p where p.sirutasup=1) " +
//            "and judet=(select d.judet from localitate d where d.tip=40 and d.denumireLocalitate like %?2%);", nativeQuery = true)

    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate)," +
            "b as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)," +
            "c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b)," +
            "d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c)," +
            "e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)" +
            " select * from localitate as e where e.denumireLocalitate like %?1% and e.sirutasup<>1 " +
            "and e.sirutasup not in(select p.siruta from localitate p where p.sirutasup=1) " +
            "and judet=(select d.judet from localitate d where d.tip=40 and d.denumireLocalitate like %?2%) order by e.tip asc;", nativeQuery = true)
    List<Localitate> findAllByLocaJud(String denLoca, String jud);

    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate z where z.judet=?1 and z.sirutasup<>1 " +
            "and z.sirutasup not in(select r.siruta from localitate r where r.sirutasup=1)),b as(\n" +
            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
            "select * from e where e.tip<>40 and e.denumireLocalitate like %?2%", nativeQuery = true)
    List<Localitate> findAllByLocaCodJud(int jud, String loca);

    @Query(value = "with a as(select *,replace(denumireLocalitate,'Â','A') as loca from localitate where tip=40),b as(\n" +
            "    select id,judet,siruta,sirutasup,tip,replace(loca,'Ş','S') as loca1 from a)\n" +
            "   ,c as (select id,judet,siruta,sirutasup,tip,replace(loca1,'Ă','A') as loca from b),\n" +
            "     d as(select id,judet,siruta,sirutasup,tip,replace(loca,'Ţ','T') as loca1 from c),\n" +
            "     e as(select id,judet,siruta,sirutasup,tip,replace(loca1,'Î','I') as denumireLocalitate from d)\n" +
            "select judet from e where e.denumireLocalitate like %?1% ;", nativeQuery = true)
    Integer findJud(String jud);

}
