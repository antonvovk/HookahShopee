package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ProductDTO;
import com.wolf.hookahshopee.dto.ProductLightDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForCitiesDTO;
import com.wolf.hookahshopee.dto.ProductQuantityForSellersDTO;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    List<ProductDTO> findAllByFinalPrice(Integer startPrice, Integer endPrice);

    List<ProductDTO> findAllByManufacturer(String manufacturerName);

    Long getQuantityByCity(String productName, String cityName);

    List<ProductQuantityForCitiesDTO> getAllQuantitiesByCities(String name);

    List<ProductQuantityForSellersDTO> getAllQuantitiesBySellers(String name);

    void create(ProductLightDTO productDTO);

    String update(ProductLightDTO productDTO, String name);

    void updateImage(String name, String imageName);

    void delete(Long id);
}
