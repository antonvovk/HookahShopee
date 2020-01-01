package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ProductItemDTO;
import com.wolf.hookahshopee.dto.ProductItemLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.ProductItemMapper;
import com.wolf.hookahshopee.model.Product;
import com.wolf.hookahshopee.model.ProductItem;
import com.wolf.hookahshopee.model.User;
import com.wolf.hookahshopee.repository.ProductItemRepository;
import com.wolf.hookahshopee.repository.ProductRepository;
import com.wolf.hookahshopee.repository.UserRepository;
import com.wolf.hookahshopee.service.ProductItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ProductItemServiceImpl(ProductItemRepository productItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.productItemRepository = productItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductItemDTO findById(Long id) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);

        if (productItem == null) {
            throw new EntityNotFoundException(ProductItem.class, "id", id.toString());
        }

        return ProductItemMapper.INSTANCE.toDto(productItem);
    }

    @Override
    public List<ProductItemDTO> findAllByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productId", productId.toString());
        }

        return ProductItemMapper.INSTANCE.toDto(productItemRepository.findByProduct(product));
    }

    @Override
    public List<ProductItemDTO> findAllBySeller(Long sellerId) {
        User user = userRepository.findById(sellerId).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "sellerId", sellerId.toString());
        }

        return ProductItemMapper.INSTANCE.toDto(productItemRepository.findBySeller(user));
    }

    @Override
    public List<ProductItemDTO> findAll() {
        return ProductItemMapper.INSTANCE.toDto(productItemRepository.findAll());
    }

    @Override
    public void create(ProductItemLightDTO productDTO) {
        Product product = productRepository.findById(productDTO.getProductId()).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productId", productDTO.getProductId().toString());
        }

        User user = userRepository.findById(productDTO.getSellerId()).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "sellerId", productDTO.getSellerId().toString());
        }

        ProductItem productItem = ProductItem.builder()
                .quantity(productDTO.getQuantity())
                .product(product)
                .seller(user)
                .build();

        productItemRepository.save(productItem);
    }

    @Override
    public void update(ProductItemLightDTO productDTO, Long id) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);

        if (productItem == null) {
            throw new EntityNotFoundException(ProductItem.class, "id", id.toString());
        }

        Product product = productRepository.findById(productDTO.getProductId()).orElse(null);

        if (product == null) {
            throw new EntityNotFoundException(Product.class, "productId", productDTO.getProductId().toString());
        }

        User user = userRepository.findById(productDTO.getSellerId()).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "sellerId", productDTO.getSellerId().toString());
        }

        productItem.setQuantity(productDTO.getQuantity());
        productItem.setProduct(product);
        productItem.setSeller(user);

        productItemRepository.save(productItem);
    }

    @Override
    public void delete(Long id) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);

        if (productItem == null) {
            throw new EntityNotFoundException(ProductItem.class, "id", id.toString());
        }

        productItemRepository.delete(productItem);
    }
}
