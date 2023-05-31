package com.myproject.BoardManagement.demo.model;


import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    @Getter
    @Setter
    @Entity
@Table(appliesTo = "user")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;


        private String department;
        private String email;
        @Column(name = "username",unique = true)
        private String username;
        private String password;
        private String firstname;
        private String lastName;
        private String role;
        @OneToMany(cascade  = CascadeType.ALL)
        private List<Action> action;


}











