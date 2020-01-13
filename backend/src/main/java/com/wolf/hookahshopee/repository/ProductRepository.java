package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Manufacturer;
import com.wolf.hookahshopee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByFinalPriceGreaterThanEqualAndFinalPriceLessThanEqual(Integer startPrice, Integer endPrice);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);

    Optional<Product> findByName(String name);
}
