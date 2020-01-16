package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Product;
import com.wolf.hookahshopee.model.ProductReservation;
import com.wolf.hookahshopee.repository.CityRepository;
import com.wolf.hookahshopee.repository.ProductRepository;
import com.wolf.hookahshopee.repository.ProductReservationRepository;
import com.wolf.hookahshopee.service.ProductReservationService;
import org.springframework.stereotype.Service;

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
    public Long getReservationQuantity(String productName, String cityName) {
        Product product = productRepository.findByName(productName).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productName", productName);
        }

        City city = cityRepository.findByName(cityName).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityName", cityName);
        }

        ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

        if (productReservation == null) {
            return 0L;
        } else {
            return productReservation.getQuantity();
        }
    }

    @Override
    public void addReservationQuantity(String productName, String cityName, Long quantity) {
        Product product = productRepository.findByName(productName).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productName", productName);
        }

        City city = cityRepository.findByName(cityName).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityName", cityName);
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
    public void removeReservationQuantity(String productName, String cityName, Long quantity) {
        Product product = productRepository.findByName(productName).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productName", productName);
        }

        City city = cityRepository.findByName(cityName).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityName", cityName);
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
    public void clearReservationQuantity(String productName, String cityName) {
        Product product = productRepository.findByName(productName).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productName", productName);
        }

        City city = cityRepository.findByName(cityName).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityName", cityName);
        }

        ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

        if (productReservation == null) {
            productReservation = ProductReservation.builder()
                    .quantity(0L)
                    .product(product)
                    .city(city)
                    .build();
        }

        productReservation.clearReservation();
        productReservationRepository.save(productReservation);
    }
}
