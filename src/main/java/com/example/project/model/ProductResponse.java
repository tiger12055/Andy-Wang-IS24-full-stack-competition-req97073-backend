package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productNumber;
    private String productName;
    private String scrumMaster;
    private String productOwner;
    private List<String> developerNames;
    private String startDate;
    private Methodology methodology;
}