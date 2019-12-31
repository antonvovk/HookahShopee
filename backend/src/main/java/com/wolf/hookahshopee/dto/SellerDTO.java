package com.wolf.hookahshopee.dto;

import com.wolf.hookahshopee.model.Role;
import lombok.Data;

@Data
public class SellerDTO {

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String password;

    private Role role;

    private CityDTO city;
}
