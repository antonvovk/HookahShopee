package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {

    ManufacturerDTO findById(Long id);

    List<ManufacturerDTO> findAll();

    void create(ManufacturerDTO manufacturerDTO);

    void update(ManufacturerDTO manufacturerDTO, Long id);

    void delete(Long id);
}
