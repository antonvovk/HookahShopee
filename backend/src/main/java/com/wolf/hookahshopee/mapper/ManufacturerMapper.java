package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.ManufacturerDTO;
import com.wolf.hookahshopee.model.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManufacturerMapper extends IMapper<Manufacturer, ManufacturerDTO> {

    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);
}
