package com.wolf.hookahshopee.product.service;

import com.wolf.hookahshopee.legacy.dto.PageDTO;
import com.wolf.hookahshopee.product.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    PageDTO<ProductDTO> getAll(ProductListRequestDTO request, Pageable pageable);

    PageDTO<ProductLightDTO> getAllLight(ProductListRequestDTO request, Pageable pageable);

    UUID create(ProductCreateDTO dto);

    void update(ProductUpdateDTO dto);

    void updateImage(UUID uuid, String imageName);

    void updateQuantity(UUID uuid, UUID sellerUUID, Long quantity);

    void delete(UUID uuid);
}
