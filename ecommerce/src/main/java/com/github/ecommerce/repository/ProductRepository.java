package com.github.ecommerce.repository;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
