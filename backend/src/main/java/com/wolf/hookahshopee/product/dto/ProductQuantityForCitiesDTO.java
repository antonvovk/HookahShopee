package com.wolf.hookahshopee.product.dto;

import com.wolf.hookahshopee.legacy.dto.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityForCitiesDTO {

    private Long quantity;

    private CityDTO city;
}
