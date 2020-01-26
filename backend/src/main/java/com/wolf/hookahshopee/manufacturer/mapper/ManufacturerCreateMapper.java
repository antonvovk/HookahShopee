package com.wolf.hookahshopee.manufacturer.mapper;

import com.wolf.hookahshopee.manufacturer.dto.ManufacturerCreateDTO;
import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManufacturerCreateMapper {

    ManufacturerCreateMapper INSTANCE = Mappers.getMapper(ManufacturerCreateMapper.class);

    @Mapping(source = "name", target = "name")
    Manufacturer fromDto(ManufacturerCreateDTO source);
}
