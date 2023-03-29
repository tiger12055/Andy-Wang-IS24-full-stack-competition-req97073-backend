package com.example.project.service;

import com.example.project.entity.Product;
import com.example.project.model.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();

    ProductResponse save(Product product);

    ProductResponse updateProduct(int productNumber, Product updatedProduct);

    List<ProductResponse> findByScrumMaster(String scrumMasterName);

    List<ProductResponse> findByDeveloper(String developerName);

    ProductResponse getProductById(int productNumber);

}
