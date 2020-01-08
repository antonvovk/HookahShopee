package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Order;
import com.wolf.hookahshopee.model.OrderStatus;
import com.wolf.hookahshopee.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);

    Page<Order> findAllBySellerAndStatus(User seller, OrderStatus status, Pageable pageable);

    List<Order> findAllByClientAndStatus(User client, OrderStatus status);

    List<Order> findAllBySeller(User seller);

    List<Order> findAllByClient(User client);

    Optional<Order> findByUuid(UUID uuid);
}
