package com.wolf.hookahshopee.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private String name;

    private Integer price;

    private Integer discount;

    private Integer finalPrice;

    private String description;

    private String imageName;

    private ManufacturerDTO manufacturer;

    private List<ProductQuantityForCitiesDTO> productQuantity;
}
