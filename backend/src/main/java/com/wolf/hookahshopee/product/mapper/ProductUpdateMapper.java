package com.wolf.hookahshopee.product.mapper;

import com.wolf.hookahshopee.product.dto.ProductUpdateDTO;
import com.wolf.hookahshopee.product.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductUpdateMapper {

    ProductUpdateMapper INSTANCE = Mappers.getMapper(ProductUpdateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "discount", target = "discount")
    @Mapping(target = "finalPrice", ignore = true)
    @Mapping(target = "numberOfSales", ignore = true)
    @Mapping(source = "htmlContent", target = "htmlContent")
    @Mapping(target = "imageName", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "productItems", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "productReservations", ignore = true)
    Product fromDto(ProductUpdateDTO source);

    @AfterMapping
    default void afterLogic(ProductUpdateDTO source, @MappingTarget Product product) {
        product.setFinalPrice(source.getPrice() - source.getDiscount());
        product.setNumberOfSales(0L);
    }
}
