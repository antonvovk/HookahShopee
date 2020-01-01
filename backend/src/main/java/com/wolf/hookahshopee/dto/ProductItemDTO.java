package com.wolf.hookahshopee.dto;

import lombok.Data;

@Data
public class ProductItemDTO {

    private Long quantity;

    private ProductDTO product;

    private UserDTO seller;
}
