package com.github.ecommerce.controller;

import com.github.ecommerce.service.ProductService;
import com.github.ecommerce.service.dto.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    final ProductService productService;

    public CheckoutController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * {@code POST /checkout} : checkout products from the catalogue
     *
     * @param productIds array of product IDs
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body that has the total calculated
     * price of the sum of products with discount if applicable.
     */
    @PostMapping
    public ResponseEntity<Bill> checkoutProducts(@RequestBody String[] productIds){

        Double price = productService.getTotalBill(productIds);
        return ResponseEntity.ok().body(new Bill(price));
    }
}
