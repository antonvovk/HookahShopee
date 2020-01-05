package com.wolf.hookahshopee.dto;

import com.wolf.hookahshopee.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private OrderStatus status;

    private Long price;

    private UserDTO seller;

    private UserDTO client;

    private List<OrderItemDTO> orderItems;
}