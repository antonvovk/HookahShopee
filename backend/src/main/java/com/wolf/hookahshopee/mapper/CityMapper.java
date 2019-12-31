package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.CityDTO;
import com.wolf.hookahshopee.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends IMapper<City, CityDTO> {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
