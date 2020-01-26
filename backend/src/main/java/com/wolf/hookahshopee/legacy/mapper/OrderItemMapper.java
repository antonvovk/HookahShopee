package com.wolf.hookahshopee.legacy.mapper;

import com.wolf.hookahshopee.legacy.dto.OrderItemDTO;
import com.wolf.hookahshopee.legacy.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper extends IMapper<OrderItem, OrderItemDTO> {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
}
