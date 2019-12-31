package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.ShopDTO;
import com.wolf.hookahshopee.dto.ShopLightDTO;
import com.wolf.hookahshopee.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ShopDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(shopService.findById(id)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ShopDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(shopService.findAll()));
    }

    @Async
    @GetMapping(value = "/byCity/{cityId}")
    public CompletableFuture<ResponseEntity<List<ShopDTO>>> findAllByCity(@PathVariable(value = "cityId") Long cityId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(shopService.findAllByCity(cityId)));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ShopLightDTO shopDTO) {
        shopService.create(shopDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid ShopLightDTO shopDTO,
                                                            @PathVariable(name = "id") Long id) {
        shopService.update(shopDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        shopService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
