package com.wolf.hookahshopee.product.service;

import com.wolf.hookahshopee.city.mapper.CityMapper;
import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.city.repository.CityRepository;
import com.wolf.hookahshopee.legacy.dto.PageDTO;
import com.wolf.hookahshopee.legacy.exception.EntityNotFoundException;
import com.wolf.hookahshopee.legacy.mapper.UserMapper;
import com.wolf.hookahshopee.legacy.model.ProductItem;
import com.wolf.hookahshopee.legacy.model.Role;
import com.wolf.hookahshopee.legacy.model.User;
import com.wolf.hookahshopee.legacy.repository.ProductItemRepository;
import com.wolf.hookahshopee.legacy.repository.UserRepository;
import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import com.wolf.hookahshopee.manufacturer.repository.ManufacturerRepository;
import com.wolf.hookahshopee.product.dto.*;
import com.wolf.hookahshopee.product.mapper.ProductCreateMapper;
import com.wolf.hookahshopee.product.mapper.ProductMapper;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.repository.ProductRepository;
import com.wolf.hookahshopee.product.specification.ProductListSpecification;
import com.wolf.hookahshopee.productreservation.model.ProductReservation;
import com.wolf.hookahshopee.productreservation.repository.ProductReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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

            List<ProductQuantityForCitiesDTO> productQuantityForCities = new ArrayList<>();

            res.forEach((city, productItems) -> {
                Long quantity = productItems.stream().mapToLong(ProductItem::getQuantity).sum();

                ProductReservation productReservation = productReservationRepository.findByProductAndCity(product, city).orElse(null);

                if (productReservation != null) {
                    quantity -= productReservation.getQuantity();
                }

                productQuantityForCities.add(new ProductQuantityForCitiesDTO(quantity, CityMapper.INSTANCE.toDto(city)));
            });

            List<User> users = userRepository.findAllByRoleIn(Arrays.asList(Role.ADMIN, Role.SELLER));
            List<ProductQuantityForSellersDTO> productQuantityForSellers = new ArrayList<>();
            users.forEach(user -> {
                Long quantity = product.getProductItems().stream()
                        .filter(productItem -> productItem.getSeller().getPhoneNumber().equals(user.getPhoneNumber()))
                        .mapToLong(ProductItem::getQuantity).sum();
                productQuantityForSellers.add(new ProductQuantityForSellersDTO(quantity, UserMapper.INSTANCE.toDto(user)));
            });

            ProductDTO productDTO = ProductMapper.INSTANCE.toDto(product);
            productDTO.setProductQuantityForCities(productQuantityForCities);
            productDTO.setProductQuantityForSellers(productQuantityForSellers);
            productDTOS.add(productDTO);
        });

        return productDTOS;
    }

    @Override
    public PageDTO<ProductDTO> getAll(ProductListRequestDTO request, Pageable pageable) {
        Page<Product> userPage = productRepository.findAll(productListSpecification.getFilter(request), pageable);
        return new PageDTO<>(afterMapperLogic(userPage.getContent()), userPage.getTotalElements());
    }

    @Override
    public ProductDTO findByUUID(UUID uuid) {
        Product product = productRepository.findByUuid(uuid).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "uuid", uuid.toString());
        }

        return afterMapperLogic(Collections.singletonList(product)).get(0);
    }

    @Override
    public UUID create(ProductCreateDTO dto) {
        Manufacturer manufacturer = manufacturerRepository.findByUuid(dto.getManufacturerUUID()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerUUID", dto.getManufacturerUUID().toString());
        }

        Product product = ProductCreateMapper.INSTANCE.fromDto(dto);
        product.setManufacturer(manufacturer);
        return productRepository.save(product).getUuid();
    }

    @Override
    public void update(ProductUpdateDTO dto) {
        Product product = productRepository.findByUuid(dto.getUuid()).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "uuid", dto.getUuid().toString());
        }

        Manufacturer manufacturer = manufacturerRepository.findByUuid(dto.getManufacturerUUID()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "manufacturerUUID", dto.getManufacturerUUID().toString());
        }

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setFinalPrice(dto.getPrice() - dto.getDiscount());
        product.setHtmlContent(dto.getHtmlContent());
        product.setManufacturer(manufacturer);

        productRepository.save(product);
    }

    @Override
    public void updateImage(UUID uuid, String imageName) {
        Product product = productRepository.findByUuid(uuid).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "uuid", uuid.toString());
        }

        product.setImageName(imageName);
        productRepository.save(product);
    }

    @Override
    public void updateQuantity(UUID uuid, UUID sellerUUID, Long quantity) {
        Product product = productRepository.findByUuid(uuid).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "uuid", uuid.toString());
        }

        User seller = userRepository.findByUuid(sellerUUID).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(User.class, "sellerUUID", sellerUUID.toString());
        }

        ProductItem productItem = product.getProductItems().stream().filter(p -> p.getSeller().getUuid().equals(sellerUUID)).findFirst().orElse(null);

        if (productItem == null) {
            productItem = ProductItem.builder().quantity(quantity).product(product).seller(seller).build();
        }

        productItem.setQuantity(quantity);
        productItemRepository.save(productItem);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Product product = productRepository.findByUuid(uuid).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "uuid", uuid.toString());
        }

        productRepository.delete(product);
    }
}
