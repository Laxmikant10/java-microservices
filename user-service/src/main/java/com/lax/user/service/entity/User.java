package com.lax.user.service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    //@Transient annotation means no need to save the field in DB
    @Transient
    private List<Rating> rating = new ArrayList<>();

}
