package com.example.project.service.impl;

import com.example.project.entity.Developer;
import com.example.project.entity.Product;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.ProductResponse;
import com.example.project.repository.DeveloperRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 /**
  Implementation of the ProductService interface that handles business logic related to Product entities.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DeveloperRepository developerRepository;

    /**
      Retrieves all products from the database and maps them to ProductResponse objects.
      @return List<ProductResponse> A list of all products as ProductResponse objects.
     */
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapProductToProductResponse(product));
        }
        return productResponses;

    }

    /**
      Adds a new product to the database and returns the saved product as a ProductResponse object.
      @param product The new Product object to be saved.
      @return ProductResponse The saved product as a ProductResponse object.
     */
    public ProductResponse save(Product product) {
        // Generate a unique product number
        int productNumber = generateUniqueProductNumber();
        product.setProductNumber(productNumber);

        // Check if all developers exist in the database and are up to 5
        validateDevelopers(product.getDeveloperNames());

        // Set developers for the product
        setDevelopersForProduct(product, product.getDeveloperNames());

        // Save the product
        Product savedProduct = productRepository.save(product);

        return mapProductToProductResponse(savedProduct);
    }

     /**
      Updates an existing product in the database and returns the updated product as a ProductResponse object.
      @param productNumber The product number of the product to be updated.
      @param updatedProduct The updated Product object.
      @return ProductResponse The updated product as a ProductResponse object.
      @throws ResourceNotFoundException if no product is found with the given product number.
      */
    public ProductResponse updateProduct(int productNumber, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productNumber);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setScrumMaster(updatedProduct.getScrumMaster());
            existingProduct.setProductOwner(updatedProduct.getProductOwner());
            existingProduct.setMethodology(updatedProduct.getMethodology());

            // Check if all developers exist in the database and are up to 5
            validateDevelopers(updatedProduct.getDeveloperNames());

            // Set developers for the product
            setDevelopersForProduct(existingProduct, updatedProduct.getDeveloperNames());

            Product updatedProductInDb = productRepository.save(existingProduct);
            return mapProductToProductResponse(updatedProductInDb);
        } else {
            throw new ResourceNotFoundException("Product not found with product number: " + productNumber);
        }
    }

     /**
      Retrieves all products from the database that have a given Scrum Master name and maps them to ProductResponse objects.
      @param scrumMasterName The name of the Scrum Master to search for.
      @return List<ProductResponse> A list of all products that have the given Scrum Master name as ProductResponse objects.
      */
    @Override
    public List<ProductResponse> findByScrumMaster(String scrumMasterName) {
        List<Product> products = productRepository.findByScrumMaster(scrumMasterName);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapProductToProductResponse(product));
        }
        return productResponses;
    }

    /**
     Retrieves all products from the database that have a given Developer name and maps them to ProductResponse objects.
     @param developerName The name of the Developer to search for.
     @return List<ProductResponse> A list of all products that have the given Developer name as ProductResponse objects.
     */
    @Override
    public List<ProductResponse> findByDeveloper(String developerName) {
        Developer developer = developerRepository.findByDeveloperName(developerName);
        List<ProductResponse> productResponses = new ArrayList<>();

        if (developer != null) {
            List<Product> products = developer.getProducts();

            for (Product product : products) {
                productResponses.add(mapProductToProductResponse(product));
            }
        }
        return productResponses;
    }

     /**
      * Retrieves a single product from the database based on its product number and maps it to a ProductResponse object.
      * @param productNumber The product number of the product to retrieve.
      * @return ProductResponse The product with the given product number as a ProductResponse object.
      * @throws ResourceNotFoundException if no product is found with the given product number.
      */
     @Override
    public ProductResponse getProductById(int productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with productNumber: " + productNumber));
        return mapProductToProductResponse(product);
    }

     /**
      * Generates a unique product number for a new product.
      * @return int The unique product number.
      */
    private int generateUniqueProductNumber() {
        Integer maxProductNumber = productRepository.findMaxProductNumber();
        return (maxProductNumber == null) ? 1 : maxProductNumber + 1;
    }

     /**
      * Checks if all Developers in a list of Developers exist in the database and that there are no more than 5 of them.
      * @param developers The list of Developers to validate.
      * @throws IllegalArgumentException if one or more of the Developers in the list do not exist in the database or if there are more than 5 Developers.
      */
    private boolean areAllDevelopersExisting(List<Developer> developers) {
        for (Developer developer : developers) {
            if (developerRepository.findByDeveloperName(developer.getDeveloperName()) == null) {
                return false;
            }
        }
        return true;
    }

     /**
      * Retrieves existing Developer objects from the database for a list of Developers.
      * @param developers The list of Developers to retrieve from the database.
      * @return List<Developer> A list of existing Developer objects.
      */
    private List<Developer> getExistingDevelopers(List<Developer> developers) {
        List<Developer> existingDevelopers = new ArrayList<>();
        for (Developer developer : developers) {
            existingDevelopers.add(developerRepository.findByDeveloperName(developer.getDeveloperName()));
        }
        return existingDevelopers;
    }

     /**
      * Validates the list of developers associated with a product.
      * @param developers The list of developers to be validated.
      * @throws IllegalArgumentException if any developer is not found in the database or if the list of developers has more than 5 developers.
      */
    private void validateDevelopers(List<Developer> developers) {
        if (!areAllDevelopersExisting(developers)) {
            throw new IllegalArgumentException("Please add this new Developer(s) to server.");
        }
        if (developers.size() > 5) {
            throw new IllegalArgumentException("A product can have up to 5 developers only.");
        }
    }

     /**
      * Sets the list of developers for a product.
      * @param product The product to which the list of developers will be set.
      * @param developers The list of developers to be associated with the product.
      */
    private void setDevelopersForProduct(Product product, List<Developer> developers) {
        product.setDeveloperNames(getExistingDevelopers(developers));
    }

     /**
      * Maps a Product object to a ProductResponse object.
      * @param product The Product object to be mapped.
      * @return A new ProductResponse object with the relevant fields.
      */
    private ProductResponse mapProductToProductResponse(Product product) {
        List<String> developerNames = new ArrayList<>();
        for (Developer developer : product.getDeveloperNames()) {
            developerNames.add(developer.getDeveloperName());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String startDate = dateFormat.format(product.getStartDate());

        return new ProductResponse(
                product.getProductNumber(),
                product.getProductName(),
                product.getScrumMaster(),
                product.getProductOwner(),
                developerNames,
                startDate,
                product.getMethodology());
    }
}