package com.wolf.hookahshopee.manufacturer.repository;

import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByUuid(UUID uuid);
}
