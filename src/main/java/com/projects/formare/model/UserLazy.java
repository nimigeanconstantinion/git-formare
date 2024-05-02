package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@NamedEntityGraph(
//        name = "UserWithDetails",
//        attributeNodes = {
////                @NamedAttributeNode("orderDetail"),
//                @NamedAttributeNode("userDetailsList")
//        }
//)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "USER")
public class UserLazy implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany( fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
    @JsonManagedReference
    private Set<OrderDetail> orderDetail = new HashSet();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user" )
    @JsonManagedReference

    private Set<UserDetails> userDetailsList = new HashSet();

}