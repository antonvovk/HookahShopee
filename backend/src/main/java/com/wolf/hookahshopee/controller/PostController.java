package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.PostDTO;
import com.wolf.hookahshopee.dto.PostLightDTO;
import com.wolf.hookahshopee.service.FileStorageService;
import com.wolf.hookahshopee.service.PostService;
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
@RequestMapping(value = "/api/post")
public class PostController {

    private final PostService postService;

    private final FileStorageService fileStorageService;

    public PostController(PostService postService, FileStorageService fileStorageService) {
        this.postService = postService;
        this.fileStorageService = fileStorageService;
    }

    @Async
    @GetMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<PostDTO>> findByName(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.findByName(name)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<PostDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.findAll()));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<String>> create(@RequestBody @Valid PostLightDTO postDTO) {
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDTO)));
    }

    @Async
    @PutMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<String>> update(@PathVariable(name = "name") String name,
                                                            @RequestBody @Valid PostLightDTO postDTO) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.update(name, postDTO)));
    }

    @Async
    @PutMapping(value = "/{name}/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<String>> updateImage(@PathVariable(name = "name") String name,
                                                                 @RequestPart MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.updateImage(name, fileName)));
    }

    @Async
    @DeleteMapping(value = "/{name}")
    public CompletableFuture<ResponseEntity<String>> delete(@PathVariable(name = "name") String name) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.delete(name)));
    }
}
