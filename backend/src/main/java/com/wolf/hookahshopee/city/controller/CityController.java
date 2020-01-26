package com.wolf.hookahshopee.city.controller;

import com.wolf.hookahshopee.city.dto.CityCreateDTO;
import com.wolf.hookahshopee.city.dto.CityDTO;
import com.wolf.hookahshopee.city.dto.CityUpdateDTO;
import com.wolf.hookahshopee.city.service.CityService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/city")
@Tag(name = "City", description = "City API")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Async
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDTO.class)))
            )
    })
    public CompletableFuture<ResponseEntity<List<CityDTO>>> getAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(cityService.getAll()));
    }

    @Async
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UUID.class))
            )
    })
    public CompletableFuture<ResponseEntity<UUID>> create(@RequestBody @Valid CityCreateDTO dto) {
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(cityService.create(dto)));
    }

    @Async
    @PutMapping(value = "/update")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid CityUpdateDTO dto) {
        cityService.update(dto);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/delete")
    public CompletableFuture<ResponseEntity<Object>> delete(@RequestParam(name = "uuid") UUID uuid) {
        cityService.delete(uuid);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
