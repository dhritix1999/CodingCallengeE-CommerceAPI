package com.github.ecommerce.service.dto;

public class Bill {

    Double price;

    public Bill(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
