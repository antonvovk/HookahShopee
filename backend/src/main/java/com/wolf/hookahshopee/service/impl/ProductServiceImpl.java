package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForCitiesDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForSellersDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.CityMapper;
import com.wolf.hookahshopee.mapper.ProductMapper;
import com.wolf.hookahshopee.mapper.UserMapper;
import com.wolf.hookahshopee.model.*;
import com.wolf.hookahshopee.repository.*;
import com.wolf.hookahshopee.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ManufacturerRepository manufacturerRepository;

    private final CityRepository cityRepository;

    private final ProductItemRepository productItemRepository;

    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CityRepository cityRepository,
                              ProductItemRepository productItemRepository,
                              UserRepository userRepository) {

        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.cityRepository = cityRepository;
        this.productItemRepository = productItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "id", id.toString());
        }

        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return ProductMapper.INSTANCE.toDto(productRepository.findAll());
    }

    @Override
    public List<ProductDTO> findAllByFinalPrice(Integer startPrice, Integer endPrice) {
        return ProductMapper.INSTANCE.toDto(productRepository.findAllByFinalPriceGreaterThanEqualAndFinalPriceLessThanEqual(startPrice, endPrice));
    }

    @Override
    public List<ProductDTO> findAllByManufacturer(String manufacturerName) {
        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerName", manufacturerName);
        }

        return ProductMapper.INSTANCE.toDto(productRepository.findAllByManufacturer(manufacturer));
    }

    @Override
    public Long getQuantityByCity(String productName, String cityName) {
        City city = cityRepository.findByName(cityName).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityName", cityName);
        }

        List<User> users = userRepository.findAllByCityAndRoleIn(city, Arrays.asList(Role.SELLER, Role.ADMIN));

        return users.stream()
                .flatMap(user -> user.getProductItems().stream())
                .filter(productItem -> productItem.getProduct().getName().equals(productName))
                .mapToLong(ProductItem::getQuantity).sum();
    }

    @Override
    public List<ProductQuantityForCitiesDTO> getAllQuantitiesByCities(String name) {
        List<ProductQuantityForCitiesDTO> productQuantities = new ArrayList<>();
        List<City> cities = cityRepository.findAll();

        cities.forEach(city -> productQuantities.add(
                new ProductQuantityForCitiesDTO(getQuantityByCity(name, city.getName()), CityMapper.INSTANCE.toDto(city))
        ));

        return productQuantities;
    }

    @Override
    public List<ProductQuantityForSellersDTO> getAllQuantitiesBySellers(String name) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        List<ProductQuantityForSellersDTO> productQuantities = new ArrayList<>();
        List<ProductItem> productItems = productItemRepository.findByProduct(product);
        productItems.forEach(productItem -> productQuantities.add(
                new ProductQuantityForSellersDTO(productItem.getQuantity(), UserMapper.INSTANCE.toDto(productItem.getSeller()))
        ));
        return productQuantities;
    }

    @Override
    public void create(ProductLightDTO productDTO) {
        Manufacturer manufacturer = manufacturerRepository.findById(productDTO.getManufacturerId()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerId", productDTO.getManufacturerId().toString());
        }

        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .discount(productDTO.getDiscount())
                .finalPrice(productDTO.getPrice() - productDTO.getDiscount())
                .description(productDTO.getDescription())
                .manufacturer(manufacturer)
                .build();

        productRepository.save(product);
    }

    @Override
    public void update(ProductLightDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "id", id.toString());
        }

        Manufacturer manufacturer = manufacturerRepository.findById(productDTO.getManufacturerId()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerId", productDTO.getManufacturerId().toString());
        }

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setFinalPrice(productDTO.getPrice() - productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setManufacturer(manufacturer);

        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "id", id.toString());
        }

        productRepository.delete(product);
    }
}
