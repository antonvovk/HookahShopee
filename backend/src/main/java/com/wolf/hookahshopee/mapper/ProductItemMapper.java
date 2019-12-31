package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.ProductItemDTO;
import com.wolf.hookahshopee.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductItemMapper extends IMapper<ProductItem, ProductItemDTO> {

    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);
}
