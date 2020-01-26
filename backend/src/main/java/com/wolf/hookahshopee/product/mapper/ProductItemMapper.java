package com.wolf.hookahshopee.product.mapper;

import com.wolf.hookahshopee.product.dto.ProductItemDTO;
import com.wolf.hookahshopee.product.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductItemMapper {

    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);

    ProductItemDTO toDto(ProductItem source);

    List<ProductItemDTO> toDto(List<ProductItem> sourceList);
}
