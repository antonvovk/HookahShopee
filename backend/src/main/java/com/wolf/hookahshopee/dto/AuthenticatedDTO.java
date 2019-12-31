package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedDTO {

    private String username;

    private String token;
}
