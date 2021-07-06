package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
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
    void memberTest() {
        // Optional<Member> findByNickname(String nickname);
        Member memberByNickname = memberRepository.findByNickname(member.getNickname()).orElse(null);
        assertThat(memberByNickname).isNotNull();
        assertThat(memberByNickname.getNickname()).isEqualTo(member.getNickname());

        // Optional<Member> findByEmail(String email);
        Member memberByEmail = memberRepository.findByEmail(member.getEmail()).orElse(null);
        assertThat(memberByEmail).isNotNull();
        assertThat(memberByEmail.getEmail()).isEqualTo(member.getEmail());

        // Optional<Member> findByNicknameOrEmail(String nickname, String email);
        Member memberByNicknameOrEmail = memberRepository.findByNicknameOrEmail(member.getNickname(), member.getEmail()).orElse(null);
        assertThat(memberByNicknameOrEmail).isNotNull();
        assertThat(memberByNicknameOrEmail.getNickname()).isEqualTo(member.getNickname());
        assertThat(memberByNicknameOrEmail.getEmail()).isEqualTo(member.getEmail());

        // boolean existsByNickname(String nickname);
        assertThat(memberRepository.existsByNickname(member.getNickname())).isTrue();

        // boolean existsByEmail(String email);
        assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();

        // Page<Member> findAllByUsernameOrEmail(String username, String email, Pageable pageable);
        Page<Member> memberAllByUsernameOrEmail = memberRepository.findAllByUsernameOrEmail(member.getUsername(), member.getEmail(), Pageable.unpaged());
        assertThat(memberAllByUsernameOrEmail.getContent().size()).isEqualTo(1);

        // Page<Member> findAll(Pageable pageable);
        Page<Member> memberAll = memberRepository.findAll(Pageable.unpaged());
        assertThat(memberAll.getSize()).isEqualTo(1);
    }

    @Test
    void memberUniqueTest() {
        Member member1 = Member.builder()
                .username("username")
                .nickname("nickname1")
                .password("password")
                .phone("phone")
                .email("email1")
                .gender("gender")
                .build();
        memberRepository.save(member1);

        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void memberUniqueNicknameTest() {
        assertThatThrownBy(() -> {
            Member member1 = Member.builder()
                    .username("username")
                    .nickname("nickname")
                    .password("password")
                    .phone("phone")
                    .email("email1")
                    .gender("gender")
                    .build();
            memberRepository.save(member1);

            memberRepository.findAll();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void memberUniqueEmailTest() {
        assertThatThrownBy(() -> {
            Member member1 = Member.builder()
                    .username("username")
                    .nickname("nickname1")
                    .password("password")
                    .phone("phone")
                    .email("email")
                    .gender("gender")
                    .build();
            memberRepository.save(member1);

            memberRepository.findAll();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}