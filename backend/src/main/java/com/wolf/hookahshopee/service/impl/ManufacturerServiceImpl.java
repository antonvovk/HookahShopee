package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ManufacturerDTO;
import com.wolf.hookahshopee.exception.EntityAlreadyExistsException;
import com.wolf.hookahshopee.exception.EntityHasRelationshipsException;
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
    public ManufacturerDTO findByName(String name) {
        Manufacturer manufacturer = manufacturerRepository.findByName(name).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "name", name);
        }

        return ManufacturerMapper.INSTANCE.toDto(manufacturer);
    }

    @Override
    public List<ManufacturerDTO> findAll() {
        return ManufacturerMapper.INSTANCE.toDto(manufacturerRepository.findAll());
    }

    @Override
    public void create(ManufacturerDTO manufacturerDTO) {
        if (manufacturerRepository.existsByName(manufacturerDTO.getName())) {
            throw new EntityAlreadyExistsException(Manufacturer.class, "name", manufacturerDTO.getName());
        }

        Manufacturer manufacturer = Manufacturer.builder()
                .name(manufacturerDTO.getName())
                .build();

        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void update(String name, ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = manufacturerRepository.findByName(name).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "name", name);
        }

        manufacturer.setName(manufacturerDTO.getName());
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void updateImage(String name, String imageName) {
        Manufacturer manufacturer = manufacturerRepository.findByName(name).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "name", name);
        }

        manufacturer.setImageName(imageName);
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(String name) {
        Manufacturer manufacturer = manufacturerRepository.findByName(name).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "name", name);
        }

        if (!manufacturer.getProducts().isEmpty()) {
            throw new EntityHasRelationshipsException(Manufacturer.class, "name", name);
        }

        manufacturerRepository.delete(manufacturer);
    }
}
