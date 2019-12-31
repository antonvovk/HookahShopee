package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper extends IMapper<Product, ProductDTO> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
