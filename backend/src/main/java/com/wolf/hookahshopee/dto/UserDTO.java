package com.wolf.hookahshopee.dto;

import com.wolf.hookahshopee.model.Role;
import lombok.Data;

@Data
public class UserDTO {

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Role role;

    private CityDTO city;
}
