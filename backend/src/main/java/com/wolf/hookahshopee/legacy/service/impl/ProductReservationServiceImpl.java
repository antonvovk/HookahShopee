package com.wolf.hookahshopee.legacy.service.impl;

import com.wolf.hookahshopee.legacy.exception.EntityNotFoundException;
import com.wolf.hookahshopee.legacy.model.City;
import com.wolf.hookahshopee.legacy.model.ProductReservation;
import com.wolf.hookahshopee.legacy.repository.CityRepository;
import com.wolf.hookahshopee.legacy.repository.ProductReservationRepository;
import com.wolf.hookahshopee.legacy.service.ProductReservationService;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductReservationServiceImpl implements ProductReservationService {

    final private ProductReservationRepository productReservationRepository;

    final private ProductRepository productRepository;

    final private CityRepository cityRepository;

    ProductReservationServiceImpl(ProductReservationRepository productReservationRepository, ProductRepository productRepository, CityRepository cityRepository) {
        this.productReservationRepository = productReservationRepository;
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Long getReservationQuantity(UUID productUUID, UUID cityUUID) {
        Product product = productRepository.findByUuid(productUUID).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productUUID", productUUID.toString());
        }

        City city = cityRepository.findByUuid(cityUUID).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", cityUUID.toString());
        }

        ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

        if (productReservation == null) {
            return 0L;
        } else {
            return productReservation.getQuantity();
        }
    }

    @Override
    public void addReservationQuantity(UUID productUUID, UUID cityUUID, Long quantity) {
        Product product = productRepository.findByUuid(productUUID).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productUUID", productUUID.toString());
        }

        City city = cityRepository.findByUuid(cityUUID).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", cityUUID.toString());
        }

        ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

        if (productReservation == null) {
            productReservation = ProductReservation.builder()
                    .quantity(0L)
                    .product(product)
                    .city(city)
                    .build();
        }

        productReservation.addReservation(quantity);
        productReservationRepository.save(productReservation);
    }

    @Override
    public void removeReservationQuantity(UUID productUUID, UUID cityUUID, Long quantity) {
        Product product = productRepository.findByUuid(productUUID).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productUUID", productUUID.toString());
        }

        City city = cityRepository.findByUuid(cityUUID).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", cityUUID.toString());
        }

        ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

        if (productReservation == null) {
            productReservation = ProductReservation.builder()
                    .quantity(0L)
                    .product(product)
                    .city(city)
                    .build();
        }

        productReservation.removeReservation(quantity);
        productReservationRepository.save(productReservation);
    }

    @Override
    public void clearReservationQuantity(UUID productUUID, UUID cityUUID) {
        Product product = productRepository.findByUuid(productUUID).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productUUID", productUUID.toString());
        }

        City city = cityRepository.findByUuid(cityUUID).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", cityUUID.toString());
        }

        productReservationRepository.findByProductAndCity(product, city).ifPresent(productReservationRepository::delete);
    }
}
