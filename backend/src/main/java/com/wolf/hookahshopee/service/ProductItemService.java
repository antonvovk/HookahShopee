package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ProductItemDTO;
import com.wolf.hookahshopee.dto.ProductItemLightDTO;

import java.util.List;

public interface ProductItemService {

    ProductItemDTO findById(Long id);

    List<ProductItemDTO> findAllByProduct(Long productId);

    List<ProductItemDTO> findAllBySeller(Long sellerId);

    List<ProductItemDTO> findAll();

    void create(ProductItemLightDTO productDTO);

    void update(ProductItemLightDTO productDTO, Long id);

    void delete(Long id);
}
