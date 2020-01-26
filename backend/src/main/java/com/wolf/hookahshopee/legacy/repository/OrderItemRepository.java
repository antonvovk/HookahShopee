package com.wolf.hookahshopee.legacy.repository;

import com.wolf.hookahshopee.legacy.model.Order;
import com.wolf.hookahshopee.legacy.model.OrderItem;
import com.wolf.hookahshopee.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);

    List<OrderItem> findAllByProduct(Product product);
}
