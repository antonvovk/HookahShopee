package com.wolf.hookahshopee.legacy.repository;

import com.wolf.hookahshopee.legacy.model.ProductItem;
import com.wolf.hookahshopee.legacy.model.User;
import com.wolf.hookahshopee.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findByProduct(Product product);

    List<ProductItem> findBySeller(User seller);
}
