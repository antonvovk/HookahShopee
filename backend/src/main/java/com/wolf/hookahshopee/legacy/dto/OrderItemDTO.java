package com.wolf.hookahshopee.legacy.dto;

import com.wolf.hookahshopee.product.dto.ProductDTO;
import lombok.Data;

@Data
public class OrderItemDTO {

    private Long price;

    private Long quantity;

    private ProductDTO product;
}
