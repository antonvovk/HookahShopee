package com.wolf.hookahshopee.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;

    private Integer price;

    private Integer discount;

    private Integer finalPrice;

    private String description;

    private ManufacturerDTO manufacturer;
}
