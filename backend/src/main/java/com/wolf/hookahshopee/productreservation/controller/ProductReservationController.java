package com.wolf.hookahshopee.productreservation.controller;

import com.wolf.hookahshopee.productreservation.service.ProductReservationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/reservation")
@Tag(name = "ProductReservation", description = "ProductReservation API")
public class ProductReservationController {

    private final ProductReservationService productReservationService;

    ProductReservationController(ProductReservationService productReservationService) {
        this.productReservationService = productReservationService;
    }

    @Async
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = Long.class))
            )
    })
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
}
