package com.wolf.hookahshopee.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductLightDTO {

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    @Min(0)
    private Integer price;

    @NotNull
    @Min(0)
    private Integer discount;

    @NotBlank
    @Size(min = 10, max = 250)
    private String description;

    @NotNull
    @Min(0)
    private Long manufacturerId;
}
