package com.wolf.hookahshopee.order.mapper;

import com.wolf.hookahshopee.order.dto.OrderDTO;
import com.wolf.hookahshopee.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(Order source);

    List<OrderDTO> toDto(List<Order> sourceList);
}
