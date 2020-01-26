package com.wolf.hookahshopee.manufacturer.mapper;

import com.wolf.hookahshopee.manufacturer.dto.ManufacturerDTO;
import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ManufacturerMapper {

    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageName", target = "imageName")
    ManufacturerDTO toDto(Manufacturer source);

    List<ManufacturerDTO> toDto(List<Manufacturer> sourceList);
}
