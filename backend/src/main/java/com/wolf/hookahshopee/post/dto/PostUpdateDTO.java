package com.wolf.hookahshopee.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDTO {

    @NotNull
    private UUID uuid;

    @NotBlank
    private String name;

    @NotBlank
    private String htmlContent;
}
