package com.wolf.hookahshopee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wolf.hookahshopee.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID uuid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private OrderStatus status;

    private Long price;

    private UserDTO seller;

    private UserDTO client;

    private List<OrderItemDTO> orderItems;
}
