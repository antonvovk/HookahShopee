package com.wolf.hookahshopee.service;

public interface ProductReservationService {

    Long getReservationQuantity(String productName, String cityName);

    void addReservationQuantity(String productName, String cityName, Long quantity);

    void removeReservationQuantity(String productName, String cityName, Long quantity);

    void clearReservationQuantity(String productName, String cityName);
}
