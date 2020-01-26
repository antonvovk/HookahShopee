package com.wolf.hookahshopee.product.mapper;

import com.wolf.hookahshopee.product.dto.ProductLightDTO;
import com.wolf.hookahshopee.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductLightMapper {

    ProductLightMapper INSTANCE = Mappers.getMapper(ProductLightMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "finalPrice", target = "finalPrice")
    @Mapping(source = "imageName", target = "imageName")
    ProductLightDTO toDto(Product source);

    List<ProductLightDTO> toDto(List<Product> sourceList);
}
