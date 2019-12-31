package com.wolf.hookahshopee.controller;

import com.wolf.hookahshopee.dto.AuthenticatedDTO;
import com.wolf.hookahshopee.dto.ClientDTO;
import com.wolf.hookahshopee.dto.ClientLightDTO;
import com.wolf.hookahshopee.dto.LoginDTO;
import com.wolf.hookahshopee.security.JwtTokenProvider;
import com.wolf.hookahshopee.service.ClientService;
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
@RequestMapping(value = "/api/client")
public class ClientController {

    private final ClientService clientService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public ClientController(ClientService clientService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ClientDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(clientService.findById(id)));
    }

    @Async
    @GetMapping(value = "/byPhoneNumber/{phoneNumber}")
    public CompletableFuture<ResponseEntity<ClientDTO>> findByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(clientService.findByPhoneNumber(phoneNumber)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<ClientDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(clientService.findAll()));
    }

    @Async
    @GetMapping(value = "/byCity/{cityId}")
    public CompletableFuture<ResponseEntity<List<ClientDTO>>> findAllByCity(@PathVariable(value = "cityId") Long cityId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(clientService.findAllByCity(cityId)));
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> create(@RequestBody @Valid ClientLightDTO clientDTO) {
        clientService.create(clientDTO);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PostMapping(value = "/login")
    public CompletableFuture<ResponseEntity<AuthenticatedDTO>> login(@RequestBody @Valid LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        ClientDTO client = clientService.findByPhoneNumber(username);
        String token = jwtTokenProvider.createToken(username, client.getRole());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new AuthenticatedDTO(username, token)));
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid ClientLightDTO clientDTO,
                                                            @PathVariable(name = "id") Long id) {
        clientService.update(clientDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        clientService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
