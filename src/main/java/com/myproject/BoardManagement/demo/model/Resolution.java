package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(appliesTo="resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ResolutionId")
    private int ResolutionId;
    @Size(max = 100)
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    Reunion reunion;

}
