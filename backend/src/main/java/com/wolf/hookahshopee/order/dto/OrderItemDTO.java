package com.wolf.hookahshopee.order.dto;

import com.wolf.hookahshopee.product.dto.ProductDTO;
import lombok.Data;

@Data
public class OrderItemDTO {

    private Long price;

    private Long quantity;

    private ProductDTO product;
}
