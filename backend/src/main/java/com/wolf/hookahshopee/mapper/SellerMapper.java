package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.SellerDTO;
import com.wolf.hookahshopee.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper extends IMapper<Seller, SellerDTO> {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);
}
