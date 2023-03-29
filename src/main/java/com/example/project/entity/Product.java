//package com.example.project.entity;
//
//import com.example.project.model.Methodology;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIdentityReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "PRODUCT")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productNumber")
//public class Product {
//    @Id
//    @Column(name = "product_number")
//    private int productNumber;
//
//    @Column(name = "product_name", unique = true, nullable = false)
//    private String productName;
//
//    @Column(name = "scrum_master")
//    @NotNull(message = "Scrum Master is required")
//    private String scrumMaster;
//
//    @Column(name = "product_owner")
//    @NotNull(message = "Product Owner is required")
//    private String productOwner;
//
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(
//        name = "product_developer",
//        joinColumns = @JoinColumn(name = "product_number"),
//        inverseJoinColumns = @JoinColumn(name = "developer_id"))
//    @JsonIdentityReference(alwaysAsId = true)
//    private List<Developer> developerNames;
//
//    @Column(name = "start_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private Date startDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "methodology")
//    private Methodology methodology;
//}
package com.example.project.entity;

import com.example.project.model.Methodology;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productNumber")
public class Product {
    @Id
    @Column(name = "product_number")
    private int productNumber;

    @Column(name = "product_name", unique = true, nullable = false)
    private String productName;

    @Column(name = "scrum_master")
    @NotNull(message = "Scrum Master is required")
    private String scrumMaster;

    @Column(name = "product_owner")
    @NotNull(message = "Product Owner is required")
    private String productOwner;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_developer",
            joinColumns = @JoinColumn(name = "product_number"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    @JsonIdentityReference(alwaysAsId = true)
    private List<Developer> developerNames;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "methodology")
    private Methodology methodology;

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", productName='" + productName + '\'' +
                ", scrumMaster='" + scrumMaster + '\'' +
                ", productOwner='" + productOwner + '\'' +
                ", startDate=" + startDate +
                ", methodology=" + methodology +
                '}';
    }
}