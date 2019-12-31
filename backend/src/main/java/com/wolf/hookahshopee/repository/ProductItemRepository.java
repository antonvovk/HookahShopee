package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Product;
import com.wolf.hookahshopee.model.ProductItem;
import com.wolf.hookahshopee.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findByProduct(Product product);

    List<ProductItem> findBySeller(Seller seller);
}
