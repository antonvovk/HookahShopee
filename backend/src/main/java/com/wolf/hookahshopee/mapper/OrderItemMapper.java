package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.OrderItemDTO;
import com.wolf.hookahshopee.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper extends IMapper<OrderItem, OrderItemDTO> {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
}
