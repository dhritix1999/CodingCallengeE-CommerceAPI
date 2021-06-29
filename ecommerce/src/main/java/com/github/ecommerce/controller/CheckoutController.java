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

    @PostMapping
    public ResponseEntity<Bill> checkoutProducts(@RequestBody String[] productIds){

        Double price = productService.getTotalBill(productIds);
        return ResponseEntity.ok().body(new Bill(price));
    }
}
