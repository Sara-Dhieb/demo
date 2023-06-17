package com.myproject.BoardManagement.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.ArrayList;
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
        @ManyToMany(mappedBy = "users")
        @JsonIgnore
    private List<Reunion> reunions;
    private String department;
        private String email;
        @Column(name = "username",unique = true)
        private String username;
        private String firstname;
        private String lastName;
        private String role;
        @OneToMany(cascade  = CascadeType.ALL)
        private List<Action> action;


}











