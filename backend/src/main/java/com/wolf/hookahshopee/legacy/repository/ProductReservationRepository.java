package com.wolf.hookahshopee.legacy.repository;

import com.wolf.hookahshopee.legacy.model.City;
import com.wolf.hookahshopee.legacy.model.ProductReservation;
import com.wolf.hookahshopee.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {

    Optional<ProductReservation> findByUuid(UUID uuid);

    Optional<ProductReservation> findByProductAndCity(Product product, City city);
}
