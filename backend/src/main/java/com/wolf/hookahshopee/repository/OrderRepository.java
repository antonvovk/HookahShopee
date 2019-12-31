package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Client;
import com.wolf.hookahshopee.model.Order;
import com.wolf.hookahshopee.model.OrderStatus;
import com.wolf.hookahshopee.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllBySellerAndStatus(Seller seller, OrderStatus status);

    List<Order> findAllByClientAndStatus(Client client, OrderStatus status);

    List<Order> findAllBySeller(Seller seller);

    List<Order> findAllByClient(Client client);
}
