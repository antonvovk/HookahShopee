package com.wolf.hookahshopee.order.service;

import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.exception.LogicalException;
import com.wolf.hookahshopee.generic.PageDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO_POST;
import com.wolf.hookahshopee.order.dto.OrderItemDTO_POST;
import com.wolf.hookahshopee.order.mapper.OrderMapper;
import com.wolf.hookahshopee.order.model.Order;
import com.wolf.hookahshopee.order.model.OrderItem;
import com.wolf.hookahshopee.order.model.OrderStatus;
import com.wolf.hookahshopee.order.repository.OrderItemRepository;
import com.wolf.hookahshopee.order.repository.OrderRepository;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.repository.ProductRepository;
import com.wolf.hookahshopee.user.model.User;
import com.wolf.hookahshopee.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public PageDTO<OrderDTO> findAllByStatus(OrderStatus status, Pageable pageable) {
        Page<OrderDTO> orderDTOS = orderRepository.findAllByStatus(status, pageable).map(OrderMapper.INSTANCE::toDto);
        return new PageDTO<>(orderDTOS.toList(), orderDTOS.getTotalElements());
    }

    @Override
    public PageDTO<OrderDTO> findAllBySellerAndStatus(String sellerUsername, OrderStatus status, Pageable pageable) {
        User seller = userRepository.findByPhoneNumber(sellerUsername).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerUsername", sellerUsername);
        }

        Page<OrderDTO> orderDTOS = orderRepository.findAllBySellerAndStatus(seller, status, pageable).map(OrderMapper.INSTANCE::toDto);
        return new PageDTO<>(orderDTOS.toList(), orderDTOS.getTotalElements());
    }

    @Override
    public List<OrderDTO> findAllByClientAndStatus(String clientUsername, OrderStatus status) {
        User client = userRepository.findByPhoneNumber(clientUsername).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(User.class, "clientUsername", clientUsername);
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
    public void create(OrderDTO_POST orderDTO) {
        User client = userRepository.findByUuid(orderDTO.getClientUUID()).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(User.class, "clientUUID", orderDTO.getClientUUID().toString());
        }

        List<OrderItem> orderItems = new ArrayList<>();
        long totalPrice = 0L;

        for (OrderItemDTO_POST orderItemDTO : orderDTO.getOrderItems()) {
            Product product = productRepository.findByUuid(orderItemDTO.getProductUUID()).orElse(null);

            if (product == null) {
                throw new EntityNotFoundException(Product.class, "productUUID", orderItemDTO.getProductUUID().toString());
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
                .seller(null)
                .client(client)
                .firstName(orderDTO.getFirstName())
                .lastName(orderDTO.getLastName())
                .phoneNumber(orderDTO.getPhoneNumber())
                .email(orderDTO.getEmail())
                .deliveryType(orderDTO.getDeliveryType())
                .deliveryCity(orderDTO.getDeliveryCity())
                .deliveryDepartment(orderDTO.getDeliveryDepartment())
                .paymentType(orderDTO.getPaymentType())
                .build();

        Order savedOrder = orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void changeStatus(UUID uuid, OrderStatus status) {
        Order order = orderRepository.findByUuid(uuid).orElse(null);

        if (order == null) {
            throw new EntityNotFoundException(Order.class, "uuid", uuid.toString());
        }

        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void assignToSeller(UUID uuid, String sellerUsername) {
        Order order = orderRepository.findByUuid(uuid).orElse(null);

        if (order == null) {
            throw new EntityNotFoundException(Order.class, "uuid", uuid.toString());
        }

        User seller = userRepository.findByPhoneNumber(sellerUsername).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerUsername", sellerUsername);
        }

        if (order.getStatus() != OrderStatus.NEW || order.getSeller() != null) {
            throw new LogicalException("Order has already been taken by another seller");
        }

        order.setSeller(seller);
        order.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(order);
    }
}
