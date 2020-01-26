package com.wolf.hookahshopee.legacy.service;

import com.wolf.hookahshopee.legacy.dto.OrderDTO;
import com.wolf.hookahshopee.legacy.dto.PageDTO;
import com.wolf.hookahshopee.legacy.dto.order.OrderDTO_POST;
import com.wolf.hookahshopee.legacy.model.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDTO findById(Long id);

    List<OrderDTO> findAll();

    PageDTO<OrderDTO> findAllByStatus(OrderStatus status, Pageable pageable);

    PageDTO<OrderDTO> findAllBySellerAndStatus(String sellerUsername, OrderStatus status, Pageable pageable);

    List<OrderDTO> findAllByClientAndStatus(String clientUsername, OrderStatus status);

    List<OrderDTO> findAllBySeller(Long sellerId);

    List<OrderDTO> findAllByClient(Long clientId);

    void create(OrderDTO_POST orderDTO);

    void changeStatus(UUID uuid, OrderStatus status);

    void assignToSeller(UUID uuid, String sellerUsername);
}