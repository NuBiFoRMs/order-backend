package com.nubiform.order.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

import static com.nubiform.order.domain.Account.RoleType.ROLE_USER;

@Getter
public class Account extends User {

    public static final class RoleType {
        public static final String ROLE_USER = "ROLE_USER";
    }

    private final Member member;

    public Account(Member member) {
        super(member.getNickname(), member.getPassword(), List.of(new SimpleGrantedAuthority(ROLE_USER)));
        this.member = member;
    }

    public Account(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        Member member = new Member();
        member.setNickname(username);
        member.setPassword(password);
        this.member = member;
    }
}
