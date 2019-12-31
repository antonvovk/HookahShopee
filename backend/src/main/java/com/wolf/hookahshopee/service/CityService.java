package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO findById(Long id);

    List<CityDTO> findAll();

    void create(CityDTO cityDTO);

    void update(CityDTO cityDTO, Long id);

    void delete(Long id);
}
