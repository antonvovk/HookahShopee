package com.wolf.hookahshopee.product.dto;

import com.wolf.hookahshopee.user.dto.UserDTO;
import lombok.Data;

@Data
public class ProductItemDTO {

    private Long quantity;

    private ProductDTO product;

    private UserDTO seller;
}
