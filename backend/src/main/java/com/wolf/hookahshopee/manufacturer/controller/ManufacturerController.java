package com.wolf.hookahshopee.manufacturer.controller;

import com.wolf.hookahshopee.file.service.FileStorageService;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerCreateDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerUpdateDTO;
import com.wolf.hookahshopee.manufacturer.service.ManufacturerService;
import com.wolf.hookahshopee.post.dto.PostDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/manufacturer")
@Tag(name = "Manufacturer", description = "Manufacturer API")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    private final FileStorageService fileStorageService;

    public ManufacturerController(ManufacturerService manufacturerService, FileStorageService fileStorageService) {
        this.manufacturerService = manufacturerService;
        this.fileStorageService = fileStorageService;
    }

    @Async
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))
            )
    })
    public CompletableFuture<ResponseEntity<List<ManufacturerDTO>>> getAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(manufacturerService.getAll()));
    }

    @Async
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UUID.class))
            )
    })
    public CompletableFuture<ResponseEntity<UUID>> create(@RequestBody @Valid ManufacturerCreateDTO dto) {
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(manufacturerService.create(dto)));
    }

    @Async
    @PutMapping(value = "/update")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid ManufacturerUpdateDTO dto) {
        manufacturerService.update(dto);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Object>> updateImage(@RequestParam(name = "uuid") UUID uuid,
                                                                 @RequestPart MultipartFile file) {
        manufacturerService.updateImage(uuid, fileStorageService.storeFile(file));
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/delete")
    public CompletableFuture<ResponseEntity<Object>> delete(@RequestParam(name = "uuid") UUID uuid) {
        manufacturerService.delete(uuid);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
