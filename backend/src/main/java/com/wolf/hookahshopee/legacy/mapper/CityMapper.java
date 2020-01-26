package com.wolf.hookahshopee.legacy.mapper;

import com.wolf.hookahshopee.legacy.dto.CityDTO;
import com.wolf.hookahshopee.legacy.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends IMapper<City, CityDTO> {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
