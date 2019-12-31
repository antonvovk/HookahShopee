package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.AuthenticatedDTO;
import com.wolf.hookahshopee.dto.LoginDTO;
import com.wolf.hookahshopee.dto.SellerDTO;
import com.wolf.hookahshopee.dto.SellerLightDTO;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.security.JwtTokenProvider;
import com.wolf.hookahshopee.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/seller")
public class SellerController {

    private final SellerService sellerService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public SellerController(SellerService sellerService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.sellerService = sellerService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<SellerDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(sellerService.findById(id)));
    }

    @Async
    @GetMapping(value = "/byPhoneNumber/{phoneNumber}")
    public CompletableFuture<ResponseEntity<SellerDTO>> findByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(sellerService.findByPhoneNumber(phoneNumber)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<SellerDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(sellerService.findAll()));
    }

    @Async
    @GetMapping(value = "/role/{role}")
    public CompletableFuture<ResponseEntity<List<SellerDTO>>> findAllByRole(@PathVariable(value = "role") Role role) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(sellerService.findAllByRole(role)));
    }

    @Async
    @GetMapping(value = "/byCity/{cityId}")
    public CompletableFuture<ResponseEntity<List<SellerDTO>>> findAllByCity(@PathVariable(value = "cityId") Long cityId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(sellerService.findAllByCity(cityId)));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid SellerLightDTO sellerDTO) {
        sellerService.create(sellerDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PostMapping(value = "/login")
    public CompletableFuture<ResponseEntity<AuthenticatedDTO>> login(@RequestBody @Valid LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        SellerDTO seller = sellerService.findByPhoneNumber(username);
        String token = jwtTokenProvider.createToken(username, seller.getRole());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new AuthenticatedDTO(username, token)));
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid SellerLightDTO sellerDTO,
                                                            @PathVariable(name = "id") Long id) {
        sellerService.update(sellerDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        sellerService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
