package com.github.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discounts")
public class Discount implements Serializable {

    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private Long productCount;

    @Column(name = "discount_bundle_price")
    private Double discountBundlePrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Double getDiscountBundlePrice() {
        return discountBundlePrice;
    }

    public void setDiscountBundlePrice(Double discountBundlePrice) {
        this.discountBundlePrice = discountBundlePrice;
    }

    public String toString(){
        return "Discount(productId=" + this.getId() +
                ", productCount="+ this.getProductCount() +
                ", discountBundlePrice="+this.getDiscountBundlePrice()+ ")";
    }
}
