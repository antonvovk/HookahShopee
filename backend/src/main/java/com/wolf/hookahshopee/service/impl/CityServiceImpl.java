package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.CityDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.CityMapper;
import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.repository.CityRepository;
import com.wolf.hookahshopee.service.CityService;
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
