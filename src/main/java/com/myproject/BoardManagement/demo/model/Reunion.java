package com.myproject.BoardManagement.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
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
    //pourquoi il prend la valeur comme reuninId
    private int ReunionId;
// rechercher la reunion par la date
    // get doc by id
@JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private String time;
    private String subject;
    //1 to many
    @OneToMany(  fetch=FetchType.LAZY)
    @JoinColumn(name = "reunion_id")
//    @OneToMany(mappedBy="reunion")
    private List<files> files;
    //1 to many
    @ManyToMany
    @JoinTable(
            name = "reunion_user",
            joinColumns = @JoinColumn(name = "reunion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))

    private  List<User> users;
    @OneToOne(mappedBy = "reunion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Minute minute;
    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

}
