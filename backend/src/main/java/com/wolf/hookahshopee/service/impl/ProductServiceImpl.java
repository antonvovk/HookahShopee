package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.controller.specification.ProductListSpecification;
import com.wolf.hookahshopee.dto.*;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.CityMapper;
import com.wolf.hookahshopee.mapper.ProductMapper;
import com.wolf.hookahshopee.mapper.UserMapper;
import com.wolf.hookahshopee.model.*;
import com.wolf.hookahshopee.repository.*;
import com.wolf.hookahshopee.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ManufacturerRepository manufacturerRepository;

    private final UserRepository userRepository;

    private final ProductItemRepository productItemRepository;

    private final ProductListSpecification productListSpecification;

    private final ProductReservationRepository productReservationRepository;

    private final CityRepository cityRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              UserRepository userRepository,
                              ProductItemRepository productItemRepository,
                              ProductListSpecification productListSpecification,
                              ProductReservationRepository productReservationRepository,
                              CityRepository cityRepository) {

        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.userRepository = userRepository;
        this.productItemRepository = productItemRepository;
        this.productListSpecification = productListSpecification;
        this.productReservationRepository = productReservationRepository;
        this.cityRepository = cityRepository;
    }

    private List<ProductDTO> afterMapperLogic(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();

        products.forEach(product -> {
            Map<City, List<ProductItem>> res = product.getProductItems().stream().collect(
                    Collectors.groupingBy(
                            productItem -> productItem.getSeller().getCity()
                    )
            );

            List<ProductQuantityForCitiesDTO> productQuantities = new ArrayList<>();

            res.forEach((city, productItems) -> {
                Long quantity = productItems.stream().mapToLong(ProductItem::getQuantity).sum();

                ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

                if (productReservation != null) {
                    quantity -= productReservation.getQuantity();
                }

                productQuantities.add(new ProductQuantityForCitiesDTO(quantity, CityMapper.INSTANCE.toDto(city)));
            });

            ProductDTO productDTO = ProductMapper.INSTANCE.toDto(product);
            productDTO.setProductQuantity(productQuantities);
            productDTOS.add(productDTO);
        });

        return productDTOS;
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
    public PageDTO<ProductDTO> findAll(ProductListRequest request, Pageable pageable) {
        Page<Product> userPage = productRepository.findAll(productListSpecification.getFilter(request), pageable);
        return new PageDTO<>(afterMapperLogic(userPage.getContent()), userPage.getTotalElements());
    }

    @Override
    public List<ProductDTO> findAllByFinalPrice(Integer startPrice, Integer endPrice) {
        return afterMapperLogic(productRepository.findAllByFinalPriceGreaterThanEqualAndFinalPriceLessThanEqual(startPrice, endPrice));
    }

    @Override
    public List<ProductDTO> findAllByManufacturer(String manufacturerName) {
        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerName", manufacturerName);
        }

        return afterMapperLogic(productRepository.findAllByManufacturer(manufacturer));
    }

    @Override
    public List<ProductQuantityForSellersDTO> getAllQuantitiesBySellers(String name) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        List<User> users = userRepository.findAllByRoleIn(Arrays.asList(Role.ADMIN, Role.SELLER));
        List<ProductQuantityForSellersDTO> productQuantities = new ArrayList<>();

        users.forEach(user -> {
            Long quantity = product.getProductItems().stream()
                    .filter(productItem -> productItem.getSeller().getPhoneNumber().equals(user.getPhoneNumber()))
                    .mapToLong(ProductItem::getQuantity).sum();
            productQuantities.add(new ProductQuantityForSellersDTO(quantity, UserMapper.INSTANCE.toDto(user)));
        });

        return productQuantities;
    }

    @Override
    public void create(ProductLightDTO productDTO) {
        Manufacturer manufacturer = manufacturerRepository.findByName(productDTO.getManufacturer().getName()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerName", productDTO.getManufacturer().getName());
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
    public String update(ProductLightDTO productDTO, String name) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        Manufacturer manufacturer = manufacturerRepository.findByName(productDTO.getManufacturer().getName()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerName", productDTO.getManufacturer().getName());
        }

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setFinalPrice(productDTO.getPrice() - productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setManufacturer(manufacturer);

        return productRepository.save(product).getName();
    }

    @Override
    public void updateImage(String name, String imageName) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        product.setImageName(imageName);
        productRepository.save(product);
    }

    @Override
    public void updateQuantity(String name, String sellerUsername, Long quantity) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        User seller = userRepository.findByPhoneNumber(sellerUsername).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerUsername", sellerUsername);
        }

        ProductItem productItem = product.getProductItems().stream().filter(p -> p.getSeller().getPhoneNumber().equals(sellerUsername)).findFirst().orElse(null);

        if (productItem == null) {
            productItem = ProductItem.builder().quantity(quantity).product(product).seller(seller).build();
        }

        productItem.setQuantity(quantity);
        productItemRepository.save(productItem);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Product product = productRepository.findByName(name).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "name", name);
        }

        productRepository.delete(product);
    }
}
