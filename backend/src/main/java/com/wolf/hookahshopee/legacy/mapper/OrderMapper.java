package com.wolf.hookahshopee.legacy.mapper;

import com.wolf.hookahshopee.legacy.dto.OrderDTO;
import com.wolf.hookahshopee.legacy.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper extends IMapper<Order, OrderDTO> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
