package com.wolf.hookahshopee.legacy.mapper;

import com.wolf.hookahshopee.legacy.dto.ProductItemDTO;
import com.wolf.hookahshopee.legacy.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductItemMapper extends IMapper<ProductItem, ProductItemDTO> {

    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);
}
