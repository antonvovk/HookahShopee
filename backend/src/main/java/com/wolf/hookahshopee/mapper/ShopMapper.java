package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.ShopDTO;
import com.wolf.hookahshopee.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopMapper extends IMapper<Shop, ShopDTO> {

    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);
}
