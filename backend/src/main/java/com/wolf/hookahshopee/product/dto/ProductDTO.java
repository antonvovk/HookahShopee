package com.wolf.hookahshopee.product.dto;

import com.wolf.hookahshopee.manufacturer.dto.ManufacturerDTO;
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
public class ProductDTO {

    private UUID uuid;

    private String name;

    private Long price;

    private Long discount;

    private Long finalPrice;

    private String htmlContent;

    private String imageName;

    private ManufacturerDTO manufacturer;

    private List<ProductQuantityForCitiesDTO> productQuantityForCities;

    private List<ProductQuantityForSellersDTO> productQuantityForSellers;
}
