package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.service.ProductReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

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
    public CompletableFuture<Long> getReservationQuantity(@RequestParam(name = "productName") String productName,
                                                          @RequestParam(name = "cityName") String cityName) {
        return CompletableFuture.completedFuture(this.productReservationService.getReservationQuantity(productName, cityName));
    }

    @Async
    @PutMapping("/add")
    public CompletableFuture<ResponseEntity<Object>> addReservationQuantity(@RequestParam(name = "productName") String productName,
                                                                            @RequestParam(name = "cityName") String cityName,
                                                                            @RequestParam(name = "quantity") Long quantity) {
        productReservationService.addReservationQuantity(productName, cityName, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping("/remove")
    public CompletableFuture<ResponseEntity<Object>> removeReservationQuantity(@RequestParam(name = "productName") String productName,
                                                                               @RequestParam(name = "cityName") String cityName,
                                                                               @RequestParam(name = "quantity") Long quantity) {
        productReservationService.removeReservationQuantity(productName, cityName, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping("/clear")
    public CompletableFuture<ResponseEntity<Object>> clearReservationQuantity(@RequestParam(name = "productName") String productName,
                                                                              @RequestParam(name = "cityName") String cityName) {
        productReservationService.clearReservationQuantity(productName, cityName);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
