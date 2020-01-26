package com.wolf.hookahshopee.order.service;

import com.wolf.hookahshopee.generic.PageDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO_POST;
import com.wolf.hookahshopee.order.model.OrderStatus;
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
