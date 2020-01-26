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
public class ProductListRequestDTO {

    private Long startPrice;

    private Long endPrice;

    private List<UUID> manufacturers;

    private UUID cityUUID;

    private Boolean isOnDiscount;
}
