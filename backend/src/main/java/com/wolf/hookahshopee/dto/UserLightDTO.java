package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLightDTO {

    @NotBlank
    @Size(min = 12, max = 12)
    private String phoneNumber;

    @NotBlank
    @Size(min = 4, max = 30)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 30)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 15)
    private String password;

    @NotBlank
    private String cityName;
}
