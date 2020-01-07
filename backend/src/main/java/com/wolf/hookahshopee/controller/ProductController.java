package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForCitiesDTO;
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
    @GetMapping(value = "/{name}/quantity/byCity/{cityName}")
    public CompletableFuture<ResponseEntity<Long>> getQuantityByCity(@PathVariable(name = "name") String name,
                                                                     @PathVariable(name = "cityName") String cityName) {

        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.getQuantityByCity(name, cityName)));
    }

    @Async
    @GetMapping(value = "/{name}/quantity/byCities")
    public CompletableFuture<ResponseEntity<List<ProductQuantityForCitiesDTO>>> getQuantitiesByCities(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.getAllQuantitiesByCities(name)));
    }

    @Async
    @GetMapping(value = "/{name}/quantity/bySellers")
    public CompletableFuture<ResponseEntity<List<ProductQuantityForSellersDTO>>> getQuantitiesBySellers(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.getAllQuantitiesBySellers(name)));
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
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ProductLightDTO productDTO) {
        productService.create(productDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PutMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<String>> update(@RequestBody @Valid ProductLightDTO productDTO,
                                                            @PathVariable(name = "name") String name) {

        return CompletableFuture.completedFuture(ResponseEntity.ok(productService.update(productDTO, name)));
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
