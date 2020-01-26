package com.wolf.hookahshopee.user.dto;

import com.wolf.hookahshopee.city.dto.CityDTO;
import com.wolf.hookahshopee.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID uuid;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Role role;

    private CityDTO city;
}
