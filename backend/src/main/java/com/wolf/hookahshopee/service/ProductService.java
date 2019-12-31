package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    List<ProductDTO> findAllByFinalPrice(Integer startPrice, Integer endPrice);

    List<ProductDTO> findAllByManufacturer(Long manufacturerId);

    void create(ProductLightDTO productDTO);

    void update(ProductLightDTO productDTO, Long id);

    void delete(Long id);
}
