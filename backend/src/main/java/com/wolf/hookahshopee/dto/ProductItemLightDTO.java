package com.wolf.hookahshopee.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
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
