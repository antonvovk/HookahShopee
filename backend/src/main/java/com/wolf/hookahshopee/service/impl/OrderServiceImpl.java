package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.OrderDTO;
import com.wolf.hookahshopee.dto.OrderItemLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.OrderMapper;
import com.wolf.hookahshopee.model.*;
import com.wolf.hookahshopee.repository.OrderItemRepository;
import com.wolf.hookahshopee.repository.OrderRepository;
import com.wolf.hookahshopee.repository.ProductRepository;
import com.wolf.hookahshopee.repository.UserRepository;
import com.wolf.hookahshopee.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new EntityNotFoundException(Order.class, "id", id.toString());
        }

        return OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        return OrderMapper.INSTANCE.toDto(orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> findAllBySellerAndStatus(Long sellerId, OrderStatus status) {
        User seller = userRepository.findById(sellerId).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerId", sellerId.toString());
        }

        return OrderMapper.INSTANCE.toDto(orderRepository.findAllBySellerAndStatus(seller, status));
    }

    @Override
    public List<OrderDTO> findAllByClientAndStatus(Long clientId, OrderStatus status) {
        User client = userRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(User.class, "clientId", clientId.toString());
        }

        return OrderMapper.INSTANCE.toDto(orderRepository.findAllByClientAndStatus(client, status));
    }

    @Override
    public List<OrderDTO> findAllBySeller(Long sellerId) {
        User seller = userRepository.findById(sellerId).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerId", sellerId.toString());
        }

        return OrderMapper.INSTANCE.toDto(orderRepository.findAllBySeller(seller));
    }

    @Override
    public List<OrderDTO> findAllByClient(Long clientId) {
        User client = userRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(User.class, "clientId", clientId.toString());
        }

        return OrderMapper.INSTANCE.toDto(orderRepository.findAllByClient(client));
    }

    @Override
    public void create(Long sellerId, Long clientId, List<OrderItemLightDTO> orderItemsDTO) {
        User seller = userRepository.findById(sellerId).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerId", sellerId.toString());
        }

        User client = userRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(User.class, "clientId", clientId.toString());
        }

        List<OrderItem> orderItems = new ArrayList<>();
        long totalPrice = 0L;

        for (OrderItemLightDTO orderItemDTO : orderItemsDTO) {
            Product product = productRepository.findById(orderItemDTO.getProductId()).orElse(null);

            if (product == null) {
                throw new EntityNotFoundException(Product.class, "productId", orderItemDTO.getProductId().toString());
            }

            OrderItem orderItem = OrderItem.builder()
                    .price(orderItemDTO.getQuantity() * product.getFinalPrice())
                    .quantity(orderItemDTO.getQuantity())
                    .product(product)
                    .build();

            totalPrice += orderItemDTO.getQuantity() * product.getFinalPrice();
            orderItems.add(orderItem);
        }

        Order order = Order.builder()
                .startDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .price(totalPrice)
                .seller(seller)
                .client(client)
                .build();

        Order savedOrder = orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void changeStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new EntityNotFoundException(Order.class, "id", id.toString());
        }

        order.setStatus(status);
        orderRepository.save(order);
    }
}
