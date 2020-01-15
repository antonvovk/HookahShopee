package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLightDTO {

    @NotBlank
    @Size(min = 5, max = 200)
    private String name;

    @NotBlank
    private String htmlContent;
}
