package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.OrderDTO;
import com.wolf.hookahshopee.dto.OrderItemLightDTO;
import com.wolf.hookahshopee.model.OrderStatus;
import com.wolf.hookahshopee.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    @GetMapping(value = "/bySeller/{sellerId}/byStatus/{status}")
    public CompletableFuture<List<OrderDTO>> findAllBySellerAndStatus(@PathVariable(name = "sellerId") Long sellerId,
                                                                      @PathVariable(name = "status") OrderStatus status) {

        return CompletableFuture.completedFuture(orderService.findAllBySellerAndStatus(sellerId, status));
    }

    @Async
    @GetMapping(value = "/byClient/{clientId}/byStatus/{status}")
    public CompletableFuture<List<OrderDTO>> findAllByClientAndStatus(@PathVariable(name = "clientId") Long clientId,
                                                                      @PathVariable(name = "status") OrderStatus status) {

        return CompletableFuture.completedFuture(orderService.findAllByClientAndStatus(clientId, status));
    }

    @Async
    @GetMapping(value = "/bySeller/{sellerId}")
    public CompletableFuture<List<OrderDTO>> findAllBySeller(@PathVariable(name = "sellerId") Long sellerId) {
        return CompletableFuture.completedFuture(orderService.findAllBySeller(sellerId));
    }

    @Async
    @GetMapping(value = "/byClient/{clientId}")
    public CompletableFuture<List<OrderDTO>> findAllByClient(@PathVariable(name = "clientId") Long clientId) {
        return CompletableFuture.completedFuture(orderService.findAllByClient(clientId));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestParam(name = "sellerId") Long sellerId,
                                                            @RequestParam(name = "clientId") Long clientId,
                                                            @RequestBody List<@Valid OrderItemLightDTO> orderItemsDTO) {
        orderService.create(sellerId, clientId, orderItemsDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{id}/changeStatus/{status}")
    public CompletableFuture<ResponseEntity<Object>> changeStatus(@PathVariable(name = "id") Long id,
                                                                  @PathVariable(name = "status") OrderStatus status) {

        orderService.changeStatus(id, status);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
