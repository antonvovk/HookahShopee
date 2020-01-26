package com.wolf.hookahshopee.product.dto;

import com.wolf.hookahshopee.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityForSellersDTO {

    private Long quantity;

    private UserDTO user;
}
