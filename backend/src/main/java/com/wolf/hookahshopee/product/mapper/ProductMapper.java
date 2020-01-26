package com.wolf.hookahshopee.product.mapper;

import com.wolf.hookahshopee.product.dto.ProductDTO;
import com.wolf.hookahshopee.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "finalPrice", target = "finalPrice")
    @Mapping(source = "htmlContent", target = "htmlContent")
    @Mapping(source = "imageName", target = "imageName")
    @Mapping(source = "manufacturer", target = "manufacturer")
    ProductDTO toDto(Product source);

    List<ProductDTO> toDto(List<Product> sourceList);
}
