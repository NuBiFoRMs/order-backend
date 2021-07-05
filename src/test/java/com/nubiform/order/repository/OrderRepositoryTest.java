package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import com.nubiform.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void orderSaveTest() {
        Order order = Order.builder()
                .product("product")
                .orderDate(LocalDateTime.now())
                .build();
        orderRepository.save(order);

        Order newOrder = orderRepository.findById(order.getId()).orElse(null);

        assertThat(newOrder).isNotNull();
        assertThat(newOrder.getProduct()).isEqualTo(order.getProduct());
    }

    @Test
    void orderMemberMappingTest() {
        Member member = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member);

        Order order = Order.builder()
                .product("product")
                .orderDate(LocalDateTime.now())
                .build();
        order.setMember(member);
        orderRepository.save(order);

        Member newMember = memberRepository.findById(member.getId()).orElse(null);

        assertThat(newMember).isNotNull();
        assertThat(newMember.getOrder()).contains(order);
    }
}