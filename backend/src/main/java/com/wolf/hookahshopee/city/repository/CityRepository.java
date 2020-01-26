package com.wolf.hookahshopee.city.repository;

import com.wolf.hookahshopee.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByUuid(UUID uuid);
}
