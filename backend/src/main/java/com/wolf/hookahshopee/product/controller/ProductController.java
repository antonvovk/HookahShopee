package com.wolf.hookahshopee.product.controller;

import com.wolf.hookahshopee.file.service.FileStorageService;
import com.wolf.hookahshopee.generic.PageDTO;
import com.wolf.hookahshopee.product.dto.ProductCreateDTO;
import com.wolf.hookahshopee.product.dto.ProductDTO;
import com.wolf.hookahshopee.product.dto.ProductListRequestDTO;
import com.wolf.hookahshopee.product.dto.ProductUpdateDTO;
import com.wolf.hookahshopee.product.service.ProductService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/product")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;

    private final FileStorageService fileStorageService;

    public ProductController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @Async
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            )
    })
    public CompletableFuture<PageDTO<ProductDTO>> getAll(ProductListRequestDTO request,
                                                         @RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "size") Integer size,
                                                         @RequestParam(name = "sortColumn", required = false) String sortColumn) {
        if (sortColumn == null) {
            return CompletableFuture.completedFuture(productService.getAll(request, PageRequest.of(page, size)));
        } else {
            return CompletableFuture.completedFuture(productService.getAll(request, PageRequest.of(page, size, Sort.by(sortColumn).descending())));
        }
    }

    @Async
    @GetMapping(value = "/uuid", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
            )
    })
    public CompletableFuture<ResponseEntity<ProductDTO>> findByUUID(@RequestParam(name = "uuid") UUID uuid) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.findByUUID(uuid)));
    }

    @Async
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UUID.class))
            )
    })
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ProductCreateDTO dto) {
        productService.create(dto);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/update")
    public CompletableFuture<ResponseEntity<String>> update(@RequestBody @Valid ProductUpdateDTO dto) {
        productService.update(dto);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Object>> updateImage(@RequestParam(name = "uuid") UUID uuid,
                                                                 @RequestPart MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);
        productService.updateImage(uuid, fileName);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/updateQuantity")
    public CompletableFuture<ResponseEntity<Object>> updateQuantity(@RequestParam(name = "uuid") UUID uuid,
                                                                    @RequestParam(name = "sellerUUID") UUID sellerUUID,
                                                                    @RequestParam(name = "quantity") Long quantity) {
        productService.updateQuantity(uuid, sellerUUID, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/delete")
    public CompletableFuture<ResponseEntity<Object>> delete(@RequestParam(name = "uuid") UUID uuid) {
        productService.delete(uuid);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
