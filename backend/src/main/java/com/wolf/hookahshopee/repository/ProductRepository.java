package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Manufacturer;
import com.wolf.hookahshopee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByFinalPriceGreaterThanEqualAndFinalPriceLessThanEqual(Integer startPrice, Integer endPrice);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);
}
