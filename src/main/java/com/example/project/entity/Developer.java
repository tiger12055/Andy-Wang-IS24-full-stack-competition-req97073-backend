package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Developer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "developerName")
public class Developer {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "developerNames", cascade = CascadeType.MERGE)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Product> products;

    @Column(name = "developer_name")
    private String developerName;

}