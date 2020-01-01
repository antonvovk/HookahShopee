package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Order;
import com.wolf.hookahshopee.model.OrderStatus;
import com.wolf.hookahshopee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllBySellerAndStatus(User seller, OrderStatus status);

    List<Order> findAllByClientAndStatus(User client, OrderStatus status);

    List<Order> findAllBySeller(User seller);

    List<Order> findAllByClient(User client);
}
