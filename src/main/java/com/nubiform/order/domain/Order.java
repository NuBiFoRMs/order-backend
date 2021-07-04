package com.nubiform.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    private LocalDateTime orderDate;

    @ManyToOne
    private Member member;

    @Builder
    public Order(String product, LocalDateTime orderDate) {
        this.product = product;
        this.orderDate = orderDate;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrder().add(this);
    }
}
