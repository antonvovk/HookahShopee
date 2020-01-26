package com.wolf.hookahshopee.legacy.repository;

import com.wolf.hookahshopee.legacy.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    Optional<City> findByUuid(UUID uuid);
}
