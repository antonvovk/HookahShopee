package com.wolf.hookahshopee.manufacturer.service;

import com.wolf.hookahshopee.manufacturer.dto.ManufacturerCreateDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {

    List<ManufacturerDTO> getAll();

    UUID create(ManufacturerCreateDTO dto);

    void update(ManufacturerUpdateDTO dto);

    void updateImage(UUID uuid, String imageName);

    void delete(UUID uuid);
}
