package com.wolf.hookahshopee.legacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private UUID uuid;

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;
}
