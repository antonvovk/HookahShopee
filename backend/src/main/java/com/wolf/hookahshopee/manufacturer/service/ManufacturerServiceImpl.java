package com.wolf.hookahshopee.manufacturer.service;

import com.wolf.hookahshopee.legacy.exception.EntityHasRelationshipsException;
import com.wolf.hookahshopee.legacy.exception.EntityNotFoundException;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerCreateDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerDTO;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerUpdateDTO;
import com.wolf.hookahshopee.manufacturer.mapper.ManufacturerCreateMapper;
import com.wolf.hookahshopee.manufacturer.mapper.ManufacturerMapper;
import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import com.wolf.hookahshopee.manufacturer.repository.ManufacturerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<ManufacturerDTO> getAll() {
        return ManufacturerMapper.INSTANCE.toDto(manufacturerRepository.findAll());
    }

    @Override
    public UUID create(ManufacturerCreateDTO dto) {
        return manufacturerRepository.save(ManufacturerCreateMapper.INSTANCE.fromDto(dto)).getUuid();
    }

    @Override
    public void update(ManufacturerUpdateDTO dto) {
        Manufacturer manufacturer = manufacturerRepository.findByUuid(dto.getUuid()).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "uuid", dto.getUuid().toString());
        }


        manufacturer.setName(dto.getName());
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void updateImage(UUID uuid, String imageName) {
        Manufacturer manufacturer = manufacturerRepository.findByUuid(uuid).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "uuid", uuid.toString());
        }

        manufacturer.setImageName(imageName);
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(UUID uuid) {
        Manufacturer manufacturer = manufacturerRepository.findByUuid(uuid).orElse(null);

        if (manufacturer == null) {
            throw new EntityNotFoundException(Manufacturer.class, "uuid", uuid.toString());
        }

        if (!manufacturer.getProducts().isEmpty()) {
            throw new EntityHasRelationshipsException(Manufacturer.class, "name", manufacturer.getName());
        }

        manufacturerRepository.delete(manufacturer);
    }
}
