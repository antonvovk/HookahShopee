package com.wolf.hookahshopee.dto;

import lombok.Data;

@Data
public class ShopDTO {

    private String phoneNumber;

    private String email;

    private String schedule;

    private CityDTO city;
}
