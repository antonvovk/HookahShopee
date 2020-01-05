package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {

    ManufacturerDTO findByName(String name);

    List<ManufacturerDTO> findAll();

    void create(ManufacturerDTO manufacturerDTO);

    void update(String name, ManufacturerDTO manufacturerDTO);

    void delete(String name);
}
