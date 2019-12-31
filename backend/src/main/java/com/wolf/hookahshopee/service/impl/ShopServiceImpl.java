package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ShopDTO;
import com.wolf.hookahshopee.dto.ShopLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.ShopMapper;
import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Shop;
import com.wolf.hookahshopee.repository.CityRepository;
import com.wolf.hookahshopee.repository.ShopRepository;
import com.wolf.hookahshopee.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final CityRepository cityRepository;

    public ShopServiceImpl(ShopRepository shopRepository, CityRepository cityRepository) {
        this.shopRepository = shopRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public ShopDTO findById(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "id", id.toString());
        }

        return ShopMapper.INSTANCE.toDto(shop);
    }

    @Override
    public List<ShopDTO> findAll() {
        return ShopMapper.INSTANCE.toDto(shopRepository.findAll());
    }

    @Override
    public List<ShopDTO> findAllByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", cityId.toString());
        }

        return ShopMapper.INSTANCE.toDto(shopRepository.findAllByCity(city));
    }

    @Override
    public void create(ShopLightDTO shopDto) {
        City city = cityRepository.findById(shopDto.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", shopDto.getCityId().toString());
        }

        Shop shop = Shop.builder()
                .phoneNumber(shopDto.getPhoneNumber())
                .email(shopDto.getEmail())
                .schedule(shopDto.getSchedule())
                .city(city)
                .build();

        shopRepository.save(shop);
    }

    @Override
    public void update(ShopLightDTO shopDto, Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "id", id.toString());
        }

        City city = cityRepository.findById(shopDto.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "id", shopDto.getCityId().toString());
        }

        shop.setPhoneNumber(shopDto.getPhoneNumber());
        shop.setEmail(shopDto.getEmail());
        shop.setSchedule(shopDto.getSchedule());
        shop.setCity(city);

        shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "id", id.toString());
        }

        shopRepository.delete(shop);
    }
}
