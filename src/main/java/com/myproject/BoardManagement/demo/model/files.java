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
@Builder
@Table(appliesTo="files")
public class files {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    //save as base 64 (pdf/excel)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;
    @Lob private String fileContent;
    private String fileType;



}
