package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member);
    }

    @Test
    public void memberTest() {
        Member memberByNickname = memberRepository.findByNickname(member.getNickname()).orElse(null);
        assertNotNull(memberByNickname);
        assertEquals(member.getNickname(), memberByNickname.getNickname());

        Member memberByEmail = memberRepository.findByEmail(member.getEmail()).orElse(null);
        assertNotNull(memberByEmail);
        assertEquals(member.getEmail(), memberByEmail.getEmail());

        Member memberByNicknameOrEmail = memberRepository.findByNicknameOrEmail(member.getNickname(), member.getEmail()).orElse(null);
        assertNotNull(memberByNicknameOrEmail);
        assertEquals(member.getNickname(), memberByNicknameOrEmail.getNickname());
        assertEquals(member.getEmail(), memberByNicknameOrEmail.getEmail());

        assertTrue(memberRepository.existsByNickname(member.getNickname()));
        assertTrue(memberRepository.existsByEmail(member.getEmail()));
    }

    @Test
    public void memberUniqueTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname1")
                .password("password")
                .phone("phone")
                .email("email1")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertEquals(2, memberRepository.findAll().size());
    }

    @Test
    public void memberUniqueNicknameTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname")
                .password("password")
                .phone("phone")
                .email("email1")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertThrows(DataIntegrityViolationException.class, () -> memberRepository.findAll());
    }

    @Test
    public void memberUniqueEmailTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname1")
                .password("password")
                .phone("phone")
                .email("email")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertThrows(DataIntegrityViolationException.class, () -> memberRepository.findAll());
    }
}