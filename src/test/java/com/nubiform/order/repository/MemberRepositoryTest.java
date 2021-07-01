package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberSaveTest() {
        Member member = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).orElse(null);

        assertNotNull(findMember);
        assertEquals(member.getUsername(), findMember.getUsername());
    }

    @Test
    public void memberUniqueTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .username("username")
                .nickname("nickname1")
                .password("password")
                .phone("phone")
                .email("email1")
                .gender("gender")
                .build();
        memberRepository.save(member2);

        assertEquals(2, memberRepository.findAll().size());
    }

    @Test
    public void memberUniqueNicknameTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            Member member2 = Member.builder()
                    .username("username")
                    .nickname("nickname")
                    .password("password")
                    .phone("phone")
                    .email("email1")
                    .gender("gender")
                    .build();
            memberRepository.saveAndFlush(member2);
        });
    }

    @Test
    public void memberUniqueEmailTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            Member member2 = Member.builder()
                    .username("username")
                    .nickname("nickname1")
                    .password("password")
                    .phone("phone")
                    .email("email")
                    .gender("gender")
                    .build();
            memberRepository.saveAndFlush(member2);
        });
    }
}