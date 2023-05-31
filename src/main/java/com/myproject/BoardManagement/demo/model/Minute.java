package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(appliesTo="minute")
public class Minute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PvId;
    private Date date;
//    1 to 1
@OneToOne(cascade = CascadeType.ALL)
    private Reunion reunion;

}
