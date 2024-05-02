package com.projects.formare.repository1;

import com.projects.formare.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails,Long> {
//
//    @Query(value = "select ud from UserDetails ud where ud.user.userId=?1")
//    Set<UserDetails> getUserDetailsByUserID(Long id);
}
