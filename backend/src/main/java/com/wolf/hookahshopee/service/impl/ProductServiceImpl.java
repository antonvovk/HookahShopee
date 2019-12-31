package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.ProductMapper;
import com.wolf.hookahshopee.model.Manufacturer;
import com.wolf.hookahshopee.model.Product;
import com.wolf.hookahshopee.repository.ManufacturerRepository;
import com.wolf.hookahshopee.repository.ProductRepository;
import com.wolf.hookahshopee.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
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
    public List<ProductDTO> findAllByManufacturer(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerId", manufacturerId.toString());
        }

        return ProductMapper.INSTANCE.toDto(productRepository.findAllByManufacturer(manufacturer));
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
