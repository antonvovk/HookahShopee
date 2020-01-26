package com.wolf.hookahshopee.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListRequestDTO {

    private Long startPrice;

    private Long endPrice;

    private List<String> manufacturers;

    private String cityName;

    private Boolean isOnDiscount;
}
