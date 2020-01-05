package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductQuantityForSellersDTO {

    private Long quantity;

    private UserDTO user;
}
