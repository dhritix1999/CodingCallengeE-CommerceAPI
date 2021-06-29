package com.github.ecommerce.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    private String id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private Double unitPrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

