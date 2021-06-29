package com.github.ecommerce.repository;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the {@link Product} entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
