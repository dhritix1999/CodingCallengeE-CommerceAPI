package com.github.ecommerce.service;

import com.github.ecommerce.model.Discount;
import com.github.ecommerce.repository.DiscountRepository;
import com.github.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    public ProductService(ProductRepository productRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }

    /**
     * get the Total Price of the Products
     *
     * @param products the array of product Ids
     * @return sum of the Products prices
     */
    public Double getTotalBill(String[] products) {

        Map<String, Long> counts =
                Arrays.stream(products)
                        .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Double sum = 0.0;

        for (Map.Entry<String, Long> product : counts.entrySet()) {
            sum += getProductPrice(product.getKey(), product.getValue());
        }
        return sum;
    }


    /**
     * Calculate the price of each product
     *
     * @param productId        the id of product
     * @param productQuantity the quantity of the product
     * @return new Asset.
     */
    public Double getProductPrice(String productId, Long productQuantity) {
//        Initialize product price
        Double productPriceSum = 0.0;

        if (discountRepository.existsById(productId)) {
            Discount discountOfP = discountRepository.getById(productId);

            if (productQuantity >= discountOfP.getProductCount()) {

                //get number of times discount applies
                int applicableForDiscountCount = Math.round((productQuantity / discountOfP.getProductCount()));

                //get total prices of discount items
                productPriceSum += applicableForDiscountCount * discountOfP.getDiscountBundlePrice();

                //remove the number of items accounted for discount
                productQuantity -= applicableForDiscountCount * discountOfP.getProductCount();
            }
            //apply regular pricing for the remaining product count
            productPriceSum += discountOfP.getProduct().getUnitPrice() * productQuantity;
        } else {
            //if product exists
            if (productRepository.existsById(productId)) {
                productPriceSum += productRepository.getById(productId).getUnitPrice() * productQuantity;
            }
        }

        System.out.println(productPriceSum);
        return productPriceSum;
    }
}
