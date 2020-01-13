package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);

    PageDTO<ProductDTO> findAll(ProductListRequest request, Pageable pageable);

    List<ProductDTO> findAllByFinalPrice(Integer startPrice, Integer endPrice);

    List<ProductDTO> findAllByManufacturer(String manufacturerName);

    List<ProductQuantityForSellersDTO> getAllQuantitiesBySellers(String name);

    void create(ProductLightDTO productDTO);

    String update(ProductLightDTO productDTO, String name);

    void updateImage(String name, String imageName);

    void updateQuantity(String name, String sellerUsername, Long quantity);

    void delete(String name);
}
