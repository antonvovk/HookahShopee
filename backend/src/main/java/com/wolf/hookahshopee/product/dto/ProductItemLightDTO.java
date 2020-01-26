package com.wolf.hookahshopee.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemLightDTO {

    @NotNull
    @Min(0)
    private Long quantity;

    @NotNull
    @Min(0)
    private Long productId;

    @NotNull
    @Min(0)
    private Long sellerId;
}
