package com.wolf.hookahshopee.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CityDTO {

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;
}
