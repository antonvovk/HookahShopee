package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;
}
