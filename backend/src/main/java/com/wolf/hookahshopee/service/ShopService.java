package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ShopDTO;
import com.wolf.hookahshopee.dto.ShopLightDTO;

import java.util.List;

public interface ShopService {

    ShopDTO findById(Long id);

    List<ShopDTO> findAll();

    List<ShopDTO> findAllByCity(Long cityId);

    void create(ShopLightDTO shopDto);

    void update(ShopLightDTO shopDto, Long id);

    void delete(Long id);
}
