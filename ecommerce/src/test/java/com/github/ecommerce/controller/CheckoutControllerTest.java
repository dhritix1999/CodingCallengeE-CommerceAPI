package com.github.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import com.github.ecommerce.repository.DiscountRepository;
import com.github.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class CheckoutControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;


    final String PRODUCT_ID = "009";
    final String PRODUCT_NAME = "Titan";
    final Double UNIT_PRICE = 120.0;


    Product productToSave = new Product();
    Product savedProduct = new Product();

    final Double PRICE = 120.0;



    @BeforeEach
    public void setup() {
        productToSave = new Product();
        productToSave.setId(PRODUCT_ID);
        productToSave.setProductName(PRODUCT_NAME);
        productToSave.setUnitPrice(UNIT_PRICE);

        savedProduct = productRepository.save(productToSave);
    }

    /**
     * Return objects as json
     *
     * @param obj
     * @return jsonString
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checkout action will be conducted
     * Expect a 200 O.K response.
     */
    @Test
    public void checkoutProducts() throws Exception {
        String[] CHECKOUT_PRODUCTS = new String[] {"009"};


        System.out.println(savedProduct);
        RequestBuilder requestBuilder = post("/checkout")
                .accept(MediaType.APPLICATION_JSON)
                .content((asJsonString(CHECKOUT_PRODUCTS)))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform((requestBuilder))
                .andExpect(status().isOk())
                .andExpect(content().json("{'price':"+PRICE+"}"))
                .andDo(print());
    }

    @AfterEach
    public void cleanup(){
        discountRepository.deleteAll();
       productRepository.deleteAll();
    }
}
