package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(appliesTo="reunion")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ReunionId")
    private int ReunionId;
// rechercher la reunion par la date
    // get doc by id

    private Date date;
    private Time time;
    private String subject;
    //1 to many
    @OneToMany(  fetch=FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private List<Document> documents;
    //1 to many
    @OneToMany(  fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<User> users;
    @OneToOne(cascade = CascadeType.ALL)
    private Minute minute;
    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

}
