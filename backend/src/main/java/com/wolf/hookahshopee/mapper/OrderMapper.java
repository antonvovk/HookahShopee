package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.OrderDTO;
import com.wolf.hookahshopee.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper extends IMapper<Order, OrderDTO> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
