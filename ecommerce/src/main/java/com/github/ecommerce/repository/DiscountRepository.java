package com.github.ecommerce.repository;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, String> {


}
