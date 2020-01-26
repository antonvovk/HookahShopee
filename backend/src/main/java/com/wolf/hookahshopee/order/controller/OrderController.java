package com.wolf.hookahshopee.order.controller;

import com.wolf.hookahshopee.generic.PageDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO;
import com.wolf.hookahshopee.order.dto.OrderDTO_POST;
import com.wolf.hookahshopee.order.model.OrderStatus;
import com.wolf.hookahshopee.order.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<OrderDTO> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(orderService.findById(id));
    }

    @Async
    @GetMapping
    public CompletableFuture<List<OrderDTO>> findAll() {
        return CompletableFuture.completedFuture(orderService.findAll());
    }

    @Async
    @GetMapping(value = "/status/{status}")
    public CompletableFuture<PageDTO<OrderDTO>> findAllByStatus(@PathVariable(name = "status") OrderStatus status,
                                                                @RequestParam(name = "p") Integer page,
                                                                @RequestParam(name = "s") Integer size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").ascending());
        return CompletableFuture.completedFuture(orderService.findAllByStatus(status, pageable));
    }

    @Async
    @GetMapping(value = "/seller/{sellerUsername}/status/{status}")
    public CompletableFuture<PageDTO<OrderDTO>> findAllBySellerAndStatus(@PathVariable(name = "sellerUsername") String sellerUsername,
                                                                         @PathVariable(name = "status") OrderStatus status,
                                                                         @RequestParam(name = "p") Integer page,
                                                                         @RequestParam(name = "s") Integer size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").ascending());
        return CompletableFuture.completedFuture(orderService.findAllBySellerAndStatus(sellerUsername, status, pageable));
    }

    @Async
    @GetMapping(value = "/client/{clientUsername}/status/{status}")
    public CompletableFuture<List<OrderDTO>> findAllByClientAndStatus(@PathVariable(name = "clientUsername") String clientUsername,
                                                                      @PathVariable(name = "status") OrderStatus status) {
        return CompletableFuture.completedFuture(orderService.findAllByClientAndStatus(clientUsername, status));
    }

    @Async
    @GetMapping(value = "/seller/{sellerId}")
    public CompletableFuture<List<OrderDTO>> findAllBySeller(@PathVariable(name = "sellerId") Long sellerId) {
        return CompletableFuture.completedFuture(orderService.findAllBySeller(sellerId));
    }

    @Async
    @GetMapping(value = "/client/{clientId}")
    public CompletableFuture<List<OrderDTO>> findAllByClient(@PathVariable(name = "clientId") Long clientId) {
        return CompletableFuture.completedFuture(orderService.findAllByClient(clientId));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid OrderDTO_POST orderDTO) {
        orderService.create(orderDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{uuid}/complete")
    public CompletableFuture<ResponseEntity<Object>> completeOrder(@PathVariable(name = "uuid") UUID uuid) {
        orderService.changeStatus(uuid, OrderStatus.COMPLETED);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{uuid}/cancel")
    public CompletableFuture<ResponseEntity<Object>> cancelOrder(@PathVariable(name = "uuid") UUID uuid) {
        orderService.changeStatus(uuid, OrderStatus.CANCELED);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{uuid}/assign")
    public CompletableFuture<ResponseEntity<Object>> assignToSeller(@PathVariable(name = "uuid") UUID uuid,
                                                                    @RequestParam(name = "seller") String seller) {
        orderService.assignToSeller(uuid, seller);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
