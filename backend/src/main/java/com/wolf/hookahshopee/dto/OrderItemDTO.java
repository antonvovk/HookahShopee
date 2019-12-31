package com.wolf.hookahshopee.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long price;

    private Long quantity;

    private ProductDTO product;
}
