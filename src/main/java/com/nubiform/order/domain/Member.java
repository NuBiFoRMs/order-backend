package com.nubiform.order.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
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
    private List<Order> order = new ArrayList<>();
}
