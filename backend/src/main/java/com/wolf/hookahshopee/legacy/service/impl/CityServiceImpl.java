package com.wolf.hookahshopee.legacy.service.impl;

import com.wolf.hookahshopee.legacy.dto.CityDTO;
import com.wolf.hookahshopee.legacy.exception.EntityNotFoundException;
import com.wolf.hookahshopee.legacy.mapper.CityMapper;
import com.wolf.hookahshopee.legacy.model.City;
import com.wolf.hookahshopee.legacy.repository.CityRepository;
import com.wolf.hookahshopee.legacy.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDTO findById(Long id) {
        City city = cityRepository.findById(id).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", id.toString());
        }

        return CityMapper.INSTANCE.toDto(city);
    }

    @Override
    public List<CityDTO> findAll() {
        return CityMapper.INSTANCE.toDto(cityRepository.findAll());
    }

    @Override
    public void create(CityDTO cityDTO) {
        City city = City.builder()
                .name(cityDTO.getName())
                .build();

        cityRepository.save(city);
    }

    @Override
    public void update(CityDTO cityDTO, Long id) {
        City city = cityRepository.findById(id).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", id.toString());
        }

        city.setName(cityDTO.getName());
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        City city = cityRepository.findById(id).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", id.toString());
        }

        cityRepository.delete(city);
    }
}
