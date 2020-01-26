package com.wolf.hookahshopee.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLightDTO {

    private UUID uuid;

    private String name;

    private Long price;

    private Long discount;

    private Long finalPrice;

    private String imageName;

    private List<ProductQuantityForCitiesDTO> productQuantity;
}
