package com.wolf.hookahshopee.product.mapper;

import com.wolf.hookahshopee.product.dto.ProductCreateDTO;
import com.wolf.hookahshopee.product.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCreateMapper {

    ProductCreateMapper INSTANCE = Mappers.getMapper(ProductCreateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
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
    Product fromDto(ProductCreateDTO source);

    @AfterMapping
    default void afterLogic(ProductCreateDTO source, @MappingTarget Product product) {
        product.setFinalPrice(source.getPrice() - source.getDiscount());
        product.setNumberOfSales(0L);
    }
}