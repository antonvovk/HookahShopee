package com.wolf.hookahshopee.product.repository;

import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.model.ProductItem;
import com.wolf.hookahshopee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findByProduct(Product product);

    List<ProductItem> findBySeller(User seller);
}
