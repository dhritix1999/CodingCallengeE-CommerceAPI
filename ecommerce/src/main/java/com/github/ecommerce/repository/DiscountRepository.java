package com.github.ecommerce.repository;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data repository for the {@link Discount} entity.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {


}
