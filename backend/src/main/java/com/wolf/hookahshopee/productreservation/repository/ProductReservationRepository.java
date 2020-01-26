package com.wolf.hookahshopee.productreservation.repository;

import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.productreservation.model.ProductReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {

    Optional<ProductReservation> findByProductAndCity(Product product, City city);
}
