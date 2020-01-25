package com.wolf.hookahshopee.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String htmlContent;
}
