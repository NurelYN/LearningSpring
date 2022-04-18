package com.demo.demo.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique = true)
    private String username;

    @Column(nullable=false)
    private String password;

    @ManyToOne
    private Role role;

    @ManyToOne
    private City city;
}
