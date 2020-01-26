package com.wolf.hookahshopee.post.controller;

import com.wolf.hookahshopee.legacy.service.FileStorageService;
import com.wolf.hookahshopee.post.dto.PostCreateDTO;
import com.wolf.hookahshopee.post.dto.PostDTO;
import com.wolf.hookahshopee.post.dto.PostLightDTO;
import com.wolf.hookahshopee.post.dto.PostUpdateDTO;
import com.wolf.hookahshopee.post.service.PostService;
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
@RequestMapping(value = "/api/post")
@Tag(name = "Post", description = "Post API")
public class PostController {

    private final PostService postService;

    private final FileStorageService fileStorageService;

    public PostController(PostService postService, FileStorageService fileStorageService) {
        this.postService = postService;
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
    public CompletableFuture<ResponseEntity<List<PostDTO>>> getAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.getAll()));
    }

    @Async
    @GetMapping(value = "/all/light", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostLightDTO.class)))
            )
    })
    public CompletableFuture<ResponseEntity<List<PostLightDTO>>> getAllLight() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.getAllLight()));
    }

    @Async
    @GetMapping(value = "/uuid", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PostDTO.class))
            )
    })
    public CompletableFuture<ResponseEntity<PostDTO>> findByUUID(@RequestParam(name = "uuid") UUID uuid) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.findByUUID(uuid)));
    }

    @Async
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UUID.class))
            )
    })
    public CompletableFuture<ResponseEntity<UUID>> create(@RequestBody @Valid PostCreateDTO dto) {
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(postService.create(dto)));
    }

    @Async
    @PutMapping(value = "/update")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid PostUpdateDTO dto) {
        postService.update(dto);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping(value = "/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Object>> updateImage(@RequestParam(name = "uuid") UUID uuid,
                                                                 @RequestPart MultipartFile file) {
        postService.updateImage(uuid, fileStorageService.storeFile(file));
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/delete")
    public CompletableFuture<ResponseEntity<Object>> delete(@RequestParam(name = "uuid") UUID uuid) {
        postService.delete(uuid);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
