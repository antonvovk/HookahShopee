package com.wolf.hookahshopee.city.mapper;

import com.wolf.hookahshopee.city.dto.CityCreateDTO;
import com.wolf.hookahshopee.city.dto.CityDTO;
import com.wolf.hookahshopee.city.dto.CityUpdateDTO;
import com.wolf.hookahshopee.city.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "name", source = "name")
    CityDTO toDto(City source);

    List<CityDTO> toDto(List<City> sourceList);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "name", source = "name")
    City fromDto(CityCreateDTO source);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "name", source = "name")
    City fromDto(CityUpdateDTO source);
}
