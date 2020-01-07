package com.wolf.hookahshopee.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostLightDTO {

    @NotBlank
    @Size(min = 5, max = 200)
    private String name;

    @NotBlank
    private String htmlContent;
}
