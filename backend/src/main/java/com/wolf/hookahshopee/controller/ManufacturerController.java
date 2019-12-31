package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.ManufacturerDTO;
import com.wolf.hookahshopee.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ManufacturerDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(manufacturerService.findById(id)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ManufacturerDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(manufacturerService.findAll()));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        manufacturerService.create(manufacturerDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid ManufacturerDTO manufacturerDTO,
                                                            @PathVariable(name = "id") Long id) {
        manufacturerService.update(manufacturerDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        manufacturerService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
