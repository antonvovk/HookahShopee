package com.wolf.hookahshopee.legacy.controller;

import com.wolf.hookahshopee.legacy.dto.ProductItemDTO;
import com.wolf.hookahshopee.legacy.dto.ProductItemLightDTO;
import com.wolf.hookahshopee.legacy.service.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/productitem")
public class ProductItemController {

    private final ProductItemService productItemService;

    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ProductItemDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productItemService.findById(id)));
    }

    @Async
    @GetMapping(value = "/byProduct/{productId}")
    public CompletableFuture<ResponseEntity<List<ProductItemDTO>>> findByProduct(@PathVariable(name = "productId") Long productId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productItemService.findAllByProduct(productId)));
    }

    @Async
    @GetMapping(value = "/bySeller/{sellerId}")
    public CompletableFuture<ResponseEntity<List<ProductItemDTO>>> findBySeller(@PathVariable(name = "sellerId") Long sellerId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productItemService.findAllBySeller(sellerId)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ProductItemDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productItemService.findAll()));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ProductItemLightDTO productItemDTO) {
        productItemService.create(productItemDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid ProductItemLightDTO productItemDTO,
                                                            @PathVariable(name = "id") Long id) {
        productItemService.update(productItemDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        productItemService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
