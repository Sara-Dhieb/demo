package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Builder
@Table(appliesTo="document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    //save as base 64 (pdf/excel)
    private Date date;
    @Lob private byte[] fileContent;
    private String fileType;



}
