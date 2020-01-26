package com.wolf.hookahshopee.legacy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotBlank
    @Size(min = 12, max = 12)
    private String username;

    @NotBlank
    @Size(min = 6, max = 15)
    private String password;
}
