package com.example.project.controller;

import com.example.project.entity.Product;
import com.example.project.model.ProductResponse;
import com.example.project.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * This endpoint is used to retrieve a list of all products via HTTP GET.
     * It returns a list of ProductResponse objects in the HTTP response body along with a HTTP status code of 200 OK.
     * @return List<ProductResponse> The HTTP response object containing a list of all products and a HTTP status code of 200 OK.
     */
    @GetMapping
    @Operation(summary = "Get all products")
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    /**
     * This endpoint is used to add a new product via HTTP POST.
     * It takes a Product object as the request body and returns the saved ProductResponse object in the HTTP response body along with a HTTP status code of 201 CREATED.
     * @param product The Product object to be added to the system.
     * @return ResponseEntity<ProductResponse> The HTTP response object containing the saved ProductResponse object and a HTTP status code of 201 CREATED.
     */
    @PostMapping
    @Operation(summary = "Add a new product")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody Product product) {
        ProductResponse savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * This endpoint is used to update an existing product via HTTP PUT.
     * It takes a productNumber as a path variable and a Product object as the request body, and returns the updated ProductResponse object in the HTTP response body along with a HTTP status code of 200 OK.
     * @param productNumber The productNumber of the product to be updated.
     * @param updatedProduct The updated Product object to replace the existing product.
     * @return ResponseEntity<ProductResponse> The HTTP response object containing the updated ProductResponse object and a HTTP status code of 200 OK.
     */
    @PutMapping("/{productNumber}")
    @Operation(summary = "Update a product by product number")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productNumber") int productNumber, @Valid @RequestBody Product updatedProduct) {
        ProductResponse product = productService.updateProduct(productNumber, updatedProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * This endpoint is used to search for products by Scrum Master name via HTTP GET.
     * It takes a Scrum Master name as a request parameter and returns a list of ProductResponse objects in the HTTP response body along with a HTTP status code of 200 OK.
     * @param scrumMasterName The name of the Scrum Master to search for.
     * @return ResponseEntity<List<ProductResponse>> The HTTP response object containing a list of matching ProductResponse objects and a HTTP status code of 200 OK.
     */
    @GetMapping("/search/scrum-master")
    @Operation(summary = "Search products by Scrum Master name")
    public ResponseEntity<List<ProductResponse>> searchByScrumMaster(@RequestParam("name") String scrumMasterName) {
        List<ProductResponse> products = productService.findByScrumMaster(scrumMasterName);
        return ResponseEntity.ok(products);
    }

    /**
     * This endpoint is used to search for products by Developer name via HTTP GET.
     * It takes a Developer name as a request parameter and returns a list of ProductResponse objects in the HTTP response body along with a HTTP status code of 200 OK.
     * @param developerName The name of the Developer to search for.
     * @return ResponseEntity<List<ProductResponse>> The HTTP response object containing a list of matching ProductResponse objects and a HTTP status code of 200 OK.
     */
    @GetMapping("/search/developer")
    @Operation(summary = "Search products by Developer name")
    public ResponseEntity<List<ProductResponse>> searchByDeveloper(@RequestParam("name") String developerName) {
        List<ProductResponse> products = productService.findByDeveloper(developerName);
        return ResponseEntity.ok(products);
    }

    /**
     * This endpoint is used to retrieve a single product by product number via HTTP GET.
     * It takes a productNumber as a path variable and returns the matching ProductResponse object in the HTTP response body along with a HTTP status code of 200 OK.
     * @param productNumber The productNumber of the product to retrieve.
     * @return ResponseEntity<ProductResponse> The HTTP response object containing the matching ProductResponse object and a HTTP status code of 200 OK.
     */
    @GetMapping("/{productNumber}")
    @Operation(summary = "Get a product by product number")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productNumber") int productNumber) {
        ProductResponse product = productService.getProductById(productNumber);
        return ResponseEntity.ok(product);
    }
}
