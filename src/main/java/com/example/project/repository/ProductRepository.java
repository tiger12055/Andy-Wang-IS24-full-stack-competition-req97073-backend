package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT MAX(p.productNumber) FROM Product p")
    Integer findMaxProductNumber();
    List<Product> findByScrumMaster(String scrumMasterName);

    Optional<Product> findById(int productNumber);

}
