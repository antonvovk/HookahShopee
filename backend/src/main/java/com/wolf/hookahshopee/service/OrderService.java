package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.OrderDTO;
import com.wolf.hookahshopee.dto.OrderItemLightDTO;
import com.wolf.hookahshopee.model.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderDTO findById(Long id);

    List<OrderDTO> findAll();

    List<OrderDTO> findAllBySellerAndStatus(Long sellerId, OrderStatus status);

    List<OrderDTO> findAllByClientAndStatus(Long clientId, OrderStatus status);

    List<OrderDTO> findAllBySeller(Long sellerId);

    List<OrderDTO> findAllByClient(Long clientId);

    void create(Long sellerId, Long clientId, List<OrderItemLightDTO> orderItemsDTO);

    void changeStatus(Long id, OrderStatus status);
}
