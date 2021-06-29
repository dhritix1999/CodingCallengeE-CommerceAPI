package com.github.ecommerce.controller;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import com.github.ecommerce.repository.DiscountRepository;
import com.github.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.DisabledIfCondition;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.persistence.Column;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CheckoutControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    Product product = new Product();

    final String PRODUCT_ID = "009";
    final String PRODUCT_NAME = "Titan";
    final Double UNIT_PRICE = 120.0;

    Discount discount = new Discount();

    final Long PRODUCT_COUNT = 2L;
    final Double DISCOUNT_BUNDLE_PRICE = 200.0;

    final Double PRICE = 120.0;
    public final String[] CHECKOUT_PRODUCTS = new String[100];


    /**
     * Create a product
     */
    public void createProduct(){
        product.setId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        product.setUnitPrice(UNIT_PRICE);
        product = productRepository.save(product);
    }

    /**
     * Create a discount
     */
    public void createDiscount(){
        discount.setProduct(product);
        discount.setId(PRODUCT_ID);
        discount.setProductCount(PRODUCT_COUNT);
        discount.setDiscountBundlePrice(DISCOUNT_BUNDLE_PRICE);
        discount = discountRepository.save(discount);
    }

    /**
     * Checkout action will be conducted
     * Expect a 200 O.K response.
     */
    @Test
    public void checkoutProducts() throws Exception {

        CHECKOUT_PRODUCTS[0] = PRODUCT_ID;

        RequestBuilder requestBuilder = post("/checkout/")
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(CHECKOUT_PRODUCTS))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform((requestBuilder)).andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(PRICE));
    }

    @AfterEach
    public void cleanup(){
       productRepository.deleteAll();
       discountRepository.deleteAll();
    }
}
