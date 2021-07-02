package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNicknameOrEmail(String nickname, String email);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Page<Member> findByUsernameOrEmail(String username, String email, Pageable pageable);
}
