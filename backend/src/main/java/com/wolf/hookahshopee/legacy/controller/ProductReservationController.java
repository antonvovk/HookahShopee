package com.wolf.hookahshopee.legacy.controller;

import com.wolf.hookahshopee.legacy.service.ProductReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/reservation")
public class ProductReservationController {

    private final ProductReservationService productReservationService;

    ProductReservationController(ProductReservationService productReservationService) {
        this.productReservationService = productReservationService;
    }

    @Async
    @GetMapping
    public CompletableFuture<Long> getReservationQuantity(@RequestParam(name = "productUUID") UUID productUUID,
                                                          @RequestParam(name = "cityUUID") UUID cityUUID) {
        return CompletableFuture.completedFuture(this.productReservationService.getReservationQuantity(productUUID, cityUUID));
    }

    @Async
    @PutMapping("/add")
    public CompletableFuture<ResponseEntity<Object>> addReservationQuantity(@RequestParam(name = "productUUID") UUID productUUID,
                                                                            @RequestParam(name = "cityUUID") UUID cityUUID,
                                                                            @RequestParam(name = "quantity") Long quantity) {
        productReservationService.addReservationQuantity(productUUID, cityUUID, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping("/remove")
    public CompletableFuture<ResponseEntity<Object>> removeReservationQuantity(@RequestParam(name = "productUUID") UUID productUUID,
                                                                               @RequestParam(name = "cityUUID") UUID cityUUID,
                                                                               @RequestParam(name = "quantity") Long quantity) {
        productReservationService.removeReservationQuantity(productUUID, cityUUID, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping("/clear")
    public CompletableFuture<ResponseEntity<Object>> clearReservationQuantity(@RequestParam(name = "productUUID") UUID productUUID,
                                                                              @RequestParam(name = "cityUUID") UUID cityUUID) {
        productReservationService.clearReservationQuantity(productUUID, cityUUID);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
