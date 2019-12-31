package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ManufacturerDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.ManufacturerMapper;
import com.wolf.hookahshopee.model.Manufacturer;
import com.wolf.hookahshopee.repository.ManufacturerRepository;
import com.wolf.hookahshopee.service.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public ManufacturerDTO findById(Long id) throws EntityNotFoundException {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "id", id.toString());
        }

        return ManufacturerMapper.INSTANCE.toDto(manufacturer);
    }

    @Override
    public List<ManufacturerDTO> findAll() {
        return ManufacturerMapper.INSTANCE.toDto(manufacturerRepository.findAll());
    }

    @Override
    public void create(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(manufacturerDTO.getName())
                .build();

        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void update(ManufacturerDTO manufacturerDTO, Long id) throws EntityNotFoundException {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "id", id.toString());
        }

        manufacturer.setName(manufacturerDTO.getName());
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "id", id.toString());
        }

        manufacturerRepository.delete(manufacturer);
    }
}
