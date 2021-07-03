package com.nubiform.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;

    private String phone;

    @Column(unique = true)
    private String email;

    private String gender;

    @OneToMany(mappedBy = "member")
    private Set<Order> order = new HashSet<>();

    @Builder
    public Member(String username, String nickname, String password, String phone, String email, String gender) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }
}
