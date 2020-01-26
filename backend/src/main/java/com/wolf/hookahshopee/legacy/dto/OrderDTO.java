package com.wolf.hookahshopee.legacy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wolf.hookahshopee.legacy.model.DeliveryType;
import com.wolf.hookahshopee.legacy.model.OrderStatus;
import com.wolf.hookahshopee.legacy.model.PaymentType;
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

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private DeliveryType deliveryType;

    private String deliveryCity;

    private String deliveryDepartment;

    private PaymentType paymentType;
}
