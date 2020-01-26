package com.wolf.hookahshopee.legacy.dto.order;

import com.wolf.hookahshopee.legacy.model.DeliveryType;
import com.wolf.hookahshopee.legacy.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO_POST {

    private UUID clientUUID;

    private List<OrderItemDTO_POST> orderItems;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private DeliveryType deliveryType;

    private String deliveryCity;

    private String deliveryDepartment;

    private PaymentType paymentType;
}
