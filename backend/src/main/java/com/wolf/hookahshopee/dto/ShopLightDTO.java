package com.wolf.hookahshopee.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ShopLightDTO {

    @NotBlank
    @Size(min = 4, max = 30)
    private String phoneNumber;

    @NotBlank
    @Size(min = 4, max = 30)
    private String email;

    @NotBlank
    @Size(min = 10, max = 100)
    private String schedule;

    @NotNull
    @Min(0)
    private Long cityId;
}
