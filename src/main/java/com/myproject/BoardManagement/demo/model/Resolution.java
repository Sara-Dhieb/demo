package com.myproject.BoardManagement.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(appliesTo = "resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ResolutionId")
    private int ResolutionId;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    Reunion reunion;

}
