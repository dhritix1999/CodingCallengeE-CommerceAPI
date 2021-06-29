package com.github.ecommerce.repository;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@SpringBootTest
public class DiscountRepositoryTest {

    @Autowired
    DiscountRepository discountRepository;

    public final Long PRODUCT_COUNT = 3L;
    public final Double DISCOUNT_BUNDLE_PRICE = 100.0;

    @Autowired
    ProductRepository productRepository;

    public final String PRODUCT_ID = "009";
    public final String PRODUCT_NAME = "Titan";
    public final Double UNIT_PRICE = 120.0;

    public Product product = new Product();
    public Product savedProduct = new Product();

    public Discount discountToSave = new Discount();
    public Discount savedDiscount = new Discount();


    /**
     * create product
     */
    public void createProduct(){

    }


    @BeforeEach
    public void setup(){
        product = new Product();
        product.setId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setUnitPrice(UNIT_PRICE);

        savedProduct = productRepository.save(product);


        discountToSave = new Discount();
        discountToSave.setId(savedProduct.getId());
        discountToSave.setProduct(savedProduct);
        discountToSave.setProductCount(PRODUCT_COUNT);
        discountToSave.setDiscountBundlePrice(DISCOUNT_BUNDLE_PRICE);

        savedDiscount = discountRepository.save(discountToSave);
    }

    /**
     * check if discount exists
     */
    @Test
    public void checkDiscount(){
        assert discountRepository.findById(savedProduct.getId()).isPresent();
    }

    @AfterEach
    public void cleanup(){
        discountRepository.deleteAll();
        productRepository.deleteAll();
    }


}
