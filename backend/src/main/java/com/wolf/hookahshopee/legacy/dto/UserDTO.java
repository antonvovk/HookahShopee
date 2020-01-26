package com.wolf.hookahshopee.legacy.dto;

import com.wolf.hookahshopee.legacy.model.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {

    private UUID uuid;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Role role;

    private CityDTO city;
}
