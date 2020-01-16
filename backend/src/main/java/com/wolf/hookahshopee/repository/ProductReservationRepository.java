package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Product;
import com.wolf.hookahshopee.model.ProductReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {

    Optional<ProductReservation> findByUuid(UUID uuid);

    Optional<ProductReservation> findByProductAndCity(Product product, City city);
}
