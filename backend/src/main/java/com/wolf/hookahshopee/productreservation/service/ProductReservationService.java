package com.wolf.hookahshopee.productreservation.service;

import java.util.UUID;

public interface ProductReservationService {

    Long getReservationQuantity(UUID productUUID, UUID cityUUID);

    void addReservationQuantity(UUID productUUID, UUID cityUUID, Long quantity);

    void removeReservationQuantity(UUID productUUID, UUID cityUUID, Long quantity);
}
