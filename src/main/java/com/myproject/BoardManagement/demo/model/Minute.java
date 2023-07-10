package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;
    @Lob private String fileContent;
    private String name;


//    1 to 1
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "reunion_id")
private Reunion reunion;

}
