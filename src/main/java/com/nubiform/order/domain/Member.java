package com.nubiform.order.domain;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;

    private String phone;

    @Column(unique = true)
    private String email;

    private String gender;
}
