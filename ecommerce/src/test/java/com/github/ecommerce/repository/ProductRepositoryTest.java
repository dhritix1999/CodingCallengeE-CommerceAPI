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
    public ProductRepository productRepository;

    final String PRODUCT_ID = "009";
    final String PRODUCT_NAME = "Titan";
    final Double UNIT_PRICE = 120.0;

    Product product;

    /**
     * create product
     */
    @BeforeEach
    public void setup(){
        product = new Product();
        product.setId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setUnitPrice(UNIT_PRICE);

       product = productRepository.save(product);
    }

    /**
     * Product details shall be saved correctly.
     */
    @Test
    public void save(){
        Product product = productRepository.findById(PRODUCT_ID).get();
        assert (product.getProductName().equals(PRODUCT_NAME));
        assert (product.getUnitPrice().equals(UNIT_PRICE));
    }

    @AfterEach
    public void cleanup(){
        productRepository.deleteAll();
    }


}
