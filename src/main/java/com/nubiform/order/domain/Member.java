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
@NamedEntityGraph(name = "Member.order", attributeNodes = {
        @NamedAttributeNode("order")
})
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String username;

    @Column(unique = true, length = 30)
    private String nickname;

    private String password;

    @Column(length = 20)
    private String phone;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
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
