package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForSellersDTO;
import com.wolf.hookahshopee.service.FileStorageService;
import com.wolf.hookahshopee.service.ProductService;
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
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    private final FileStorageService fileStorageService;

    public ProductController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ProductDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.findById(id)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ProductDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.findAll()));
    }

    @Async
    @GetMapping(value = "/byFinalPrice/{startPrice}/{endPrice}")
    public CompletableFuture<ResponseEntity<List<ProductDTO>>> findAllByFinalPrice(@PathVariable(name = "startPrice") Integer startPrice,
                                                                                   @PathVariable(name = "endPrice") Integer endPrice) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.findAllByFinalPrice(startPrice, endPrice)));
    }

    @Async
    @GetMapping(value = "/byManufacturer/{manufacturerName}")
    public CompletableFuture<ResponseEntity<List<ProductDTO>>> findAllByManufacturer(@PathVariable(name = "manufacturerName") String manufacturerName) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.findAllByManufacturer(manufacturerName)));
    }

    @Async
    @GetMapping(value = "/{name}/quantity/bySellers")
    public CompletableFuture<ResponseEntity<List<ProductQuantityForSellersDTO>>> getQuantitiesBySellers(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.getAllQuantitiesBySellers(name)));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ProductLightDTO productDTO) {
        productService.create(productDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<String>> update(@RequestBody @Valid ProductLightDTO productDTO,
                                                            @PathVariable(name = "name") String name) {
        productService.update(productDTO, name);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{name}/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Object>> updateImage(@PathVariable(name = "name") String name,
                                                                 @RequestPart MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);
        productService.updateImage(name, fileName);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/{name}/updateQuantity")
    public CompletableFuture<ResponseEntity<Object>> updateQuantity(@PathVariable(name = "name") String name,
                                                                    @RequestParam(name = "seller") String seller,
                                                                    @RequestParam(name = "quantity") Long quantity) {
        productService.updateQuantity(name, seller, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "name") String name) {
        productService.delete(name);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
