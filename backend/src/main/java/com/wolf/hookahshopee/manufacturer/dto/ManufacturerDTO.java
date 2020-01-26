package com.wolf.hookahshopee.manufacturer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {

    private UUID uuid;

    private String name;

    private String imageName;
}
