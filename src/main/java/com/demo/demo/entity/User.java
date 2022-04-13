package com.demo.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
}
