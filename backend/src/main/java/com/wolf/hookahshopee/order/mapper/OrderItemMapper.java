package com.wolf.hookahshopee.order.mapper;

import com.wolf.hookahshopee.order.dto.OrderItemDTO;
import com.wolf.hookahshopee.order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO toDto(OrderItem source);

    List<OrderItemDTO> toDto(List<OrderItem> sourceList);
}
