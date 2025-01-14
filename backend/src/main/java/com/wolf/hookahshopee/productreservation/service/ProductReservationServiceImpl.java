package com.wolf.hookahshopee.productreservation.service;

import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.city.repository.CityRepository;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.exception.ReservationException;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.model.ProductItem;
import com.wolf.hookahshopee.product.repository.ProductRepository;
import com.wolf.hookahshopee.productreservation.model.ProductReservation;
import com.wolf.hookahshopee.productreservation.repository.ProductReservationRepository;
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
        if (quantity <= 0) {
            throw new ReservationException("Кількість для резервування повинна бути більша нуля");
        }

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

        long availableProductAmount = product.getProductItems().stream()
                .filter(productItem -> productItem.getSeller().getCity().getUuid().equals(cityUUID))
                .mapToLong(ProductItem::getQuantity)
                .sum();

        if (quantity + productReservation.getQuantity() > availableProductAmount) {
            throw new ReservationException("Недостатньо продукту в наявності");
        }

        productReservation.addReservation(quantity);
        productReservationRepository.save(productReservation);
    }

    @Override
    public void removeReservationQuantity(UUID productUUID, UUID cityUUID, Long quantity) {
        if (quantity <= 0) {
            throw new ReservationException("Кількість для резервування повинна бути більша нуля");
        }

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

        if (productReservation.getQuantity() - quantity < 0) {
            throw new ReservationException("Незарезервовано такої кількості продукту");
        }

        productReservation.removeReservation(quantity);
        productReservationRepository.save(productReservation);
    }
}
