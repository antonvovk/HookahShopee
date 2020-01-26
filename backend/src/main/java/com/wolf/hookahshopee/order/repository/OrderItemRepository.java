package com.wolf.hookahshopee.order.repository;

import com.wolf.hookahshopee.order.model.Order;
import com.wolf.hookahshopee.order.model.OrderItem;
import com.wolf.hookahshopee.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);

    List<OrderItem> findAllByProduct(Product product);
}
