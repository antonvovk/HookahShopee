package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.ManufacturerDTO;
import com.wolf.hookahshopee.service.FileStorageService;
import com.wolf.hookahshopee.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    private final FileStorageService fileStorageService;

    public ManufacturerController(ManufacturerService manufacturerService, FileStorageService fileStorageService) {
        this.manufacturerService = manufacturerService;
        this.fileStorageService = fileStorageService;
    }

    @Async
    @GetMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<ManufacturerDTO>> findByName(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(manufacturerService.findByName(name)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ManufacturerDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(manufacturerService.findAll()));
    }

    @Async
    @PutMapping(value = "/{name}/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Object>> updateImage(@PathVariable(name = "name") String name,
                                                                 @RequestPart MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);
        manufacturerService.updateImage(name, fileName);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        manufacturerService.create(manufacturerDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping
    public CompletableFuture<ResponseEntity<Object>> update(@RequestParam(name = "name") String name,
                                                            @RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        manufacturerService.update(name, manufacturerDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping
    public CompletableFuture<ResponseEntity<Object>> delete(@RequestParam(name = "name") String name) {
        manufacturerService.delete(name);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
