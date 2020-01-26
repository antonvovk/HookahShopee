package com.wolf.hookahshopee.city.service;

import com.wolf.hookahshopee.city.dto.CityCreateDTO;
import com.wolf.hookahshopee.city.dto.CityDTO;
import com.wolf.hookahshopee.city.dto.CityUpdateDTO;
import com.wolf.hookahshopee.city.mapper.CityMapper;
import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.city.repository.CityRepository;
import com.wolf.hookahshopee.legacy.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDTO> getAll() {
        return CityMapper.INSTANCE.toDto(cityRepository.findAll());
    }

    @Override
    public UUID create(CityCreateDTO dto) {
        return cityRepository.save(CityMapper.INSTANCE.fromDto(dto)).getUuid();
    }

    @Override
    public void update(CityUpdateDTO dto) {
        City city = cityRepository.findByUuid(dto.getUuid()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "uuid", dto.getUuid().toString());
        }

        city.setName(dto.getName());
        cityRepository.save(city);
    }

    @Override
    public void delete(UUID uuid) {
        City city = cityRepository.findByUuid(uuid).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "uuid", uuid.toString());
        }

        cityRepository.delete(city);
    }
}
