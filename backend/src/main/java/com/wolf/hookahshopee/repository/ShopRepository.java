package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAllByCity(City city);
}
