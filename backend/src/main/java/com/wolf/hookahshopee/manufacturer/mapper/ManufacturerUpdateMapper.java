package com.wolf.hookahshopee.manufacturer.mapper;

import com.wolf.hookahshopee.manufacturer.dto.ManufacturerUpdateDTO;
import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManufacturerUpdateMapper {

    ManufacturerUpdateMapper INSTANCE = Mappers.getMapper(ManufacturerUpdateMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    Manufacturer fromDto(ManufacturerUpdateDTO source);
}
