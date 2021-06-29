package com.github.ecommerce.repository;

import com.github.ecommerce.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    public final String PRODUCT_ID = "009";
    public final String PRODUCT_NAME = "Titan";
    public final Double UNIT_PRICE = 120.0;

    Product productToSave = new Product();
    Product savedProduct;

    /**
     * create product
     */
    @BeforeEach
    public void setup(){
        productToSave = new Product();
        productToSave.setId(PRODUCT_ID);
        productToSave.setProductName(PRODUCT_NAME);
        productToSave.setUnitPrice(UNIT_PRICE);

        savedProduct = productRepository.save(productToSave);
    }

    /**
     * Product details shall be saved correctly.
     */
    @Test
    public void getProduct(){
        assert (productRepository.findById(savedProduct.getId()).isPresent());
    }

    @AfterEach
    public void cleanup(){
        productRepository.deleteAll();
    }


}
