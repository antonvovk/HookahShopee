package com.wolf.hookahshopee.legacy.controller;

import com.wolf.hookahshopee.legacy.dto.AuthenticatedDTO;
import com.wolf.hookahshopee.legacy.dto.LoginDTO;
import com.wolf.hookahshopee.legacy.dto.UserDTO;
import com.wolf.hookahshopee.legacy.dto.UserLightDTO;
import com.wolf.hookahshopee.legacy.model.Role;
import com.wolf.hookahshopee.legacy.security.JwtTokenProvider;
import com.wolf.hookahshopee.legacy.service.UserService;
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
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Async
    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<UserDTO>> findById(@PathVariable(name = "id") Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findById(id)));
    }

    @Async
    @GetMapping(value = "/byPhoneNumber/{phoneNumber}")
    public CompletableFuture<ResponseEntity<UserDTO>> findByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findByPhoneNumber(phoneNumber)));
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<List<UserDTO>>> findAll() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findAll()));
    }

    @Async
    @GetMapping(value = "/role/{role}")
    public CompletableFuture<ResponseEntity<List<UserDTO>>> findAllByRole(@PathVariable(value = "role") Role role) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findAllByRole(role)));
    }

    @Async
    @GetMapping(value = "/sellers")
    public CompletableFuture<ResponseEntity<List<UserDTO>>> findAllSellers() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findAllSellers()));
    }

    @Async
    @GetMapping(value = "/byCity/{cityId}")
    public CompletableFuture<ResponseEntity<List<UserDTO>>> findAllByCity(@PathVariable(value = "cityId") Long cityId) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userService.findAllByCity(cityId)));
    }

    @Async
    @PostMapping(value = "/register/seller")
    public CompletableFuture<ResponseEntity<Object>> registerSeller(@RequestBody @Valid UserLightDTO sellerDTO) {
        userService.register(sellerDTO, Role.SELLER);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PostMapping(value = "/register/client")
    public CompletableFuture<ResponseEntity<Object>> registerClient(@RequestBody @Valid UserLightDTO sellerDTO) {
        userService.register(sellerDTO, Role.CLIENT);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Async
    @PostMapping(value = "/login")
    public CompletableFuture<ResponseEntity<AuthenticatedDTO>> login(@RequestBody @Valid LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        UserDTO user = userService.findByPhoneNumber(username);
        String token = jwtTokenProvider.createToken(username, user.getRole());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new AuthenticatedDTO(token, user)));
    }

    @Async
    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> update(@RequestBody @Valid UserLightDTO sellerDTO,
                                                            @PathVariable(name = "id") Long id) {
        userService.update(sellerDTO, id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Object>> delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
