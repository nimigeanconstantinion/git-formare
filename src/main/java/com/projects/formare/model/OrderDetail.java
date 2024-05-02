package com.projects.formare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)


@Entity
@Table(name = "USER_ORDER")
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",referencedColumnName = "id"
    )
    @JsonIgnore
    private UserLazy user;

    private int orderValue;

    public void setUser(UserLazy user) {
        this.user = user;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    public UserLazy getUser() {
        return user;
    }

    public int getOrderValue() {
        return orderValue;
    }
}