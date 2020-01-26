package com.wolf.hookahshopee.city.service;

import com.wolf.hookahshopee.city.dto.CityCreateDTO;
import com.wolf.hookahshopee.city.dto.CityDTO;
import com.wolf.hookahshopee.city.dto.CityUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface CityService {

    List<CityDTO> getAll();

    UUID create(CityCreateDTO dto);

    void update(CityUpdateDTO dto);

    void delete(UUID uuid);
}
