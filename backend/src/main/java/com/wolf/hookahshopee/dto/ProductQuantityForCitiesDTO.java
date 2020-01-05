package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductQuantityForCitiesDTO {

    private Long quantity;

    private CityDTO city;
}
