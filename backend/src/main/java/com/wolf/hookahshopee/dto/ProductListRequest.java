package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListRequest {

    private Integer startPrice;

    private Integer endPrice;

    private List<String> manufacturers;

    private String cityName;

    private Boolean isOnDiscount;
}
