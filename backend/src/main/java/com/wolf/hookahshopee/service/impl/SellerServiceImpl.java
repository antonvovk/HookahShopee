package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.SellerDTO;
import com.wolf.hookahshopee.dto.SellerLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.SellerMapper;
import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.model.Seller;
import com.wolf.hookahshopee.repository.CityRepository;
import com.wolf.hookahshopee.repository.SellerRepository;
import com.wolf.hookahshopee.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final CityRepository cityRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, CityRepository cityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.cityRepository = cityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(Seller.class, "id", id.toString());
        }

        return SellerMapper.INSTANCE.toDto(seller);
    }

    @Override
    public SellerDTO findByPhoneNumber(String phoneNumber) {
        Seller seller = sellerRepository.findByPhoneNumber(phoneNumber).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(Seller.class, "phoneNumber", phoneNumber);
        }

        return SellerMapper.INSTANCE.toDto(seller);
    }

    @Override
    public List<SellerDTO> findAll() {
        return SellerMapper.INSTANCE.toDto(sellerRepository.findAll());
    }

    @Override
    public List<SellerDTO> findAllByRole(Role role) {
        return SellerMapper.INSTANCE.toDto(sellerRepository.findAllByRole(role));
    }

    @Override
    public List<SellerDTO> findAllByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", cityId.toString());
        }

        return SellerMapper.INSTANCE.toDto(sellerRepository.findAllByCity(city));
    }

    @Override
    public void create(SellerLightDTO sellerDTO) {
        City city = cityRepository.findById(sellerDTO.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", sellerDTO.getCityId().toString());
        }

        Seller seller = Seller.builder()
                .phoneNumber(sellerDTO.getPhoneNumber())
                .firstName(sellerDTO.getFirstName())
                .lastName(sellerDTO.getLastName())
                .password(passwordEncoder.encode(sellerDTO.getPassword()))
                .role(sellerDTO.getRole())
                .city(city)
                .build();

        sellerRepository.save(seller);
    }

    @Override
    public void update(SellerLightDTO sellerDTO, Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(Seller.class, "id", id.toString());
        }

        City city = cityRepository.findById(sellerDTO.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", sellerDTO.getCityId().toString());
        }

        seller.setPhoneNumber(sellerDTO.getPhoneNumber());
        seller.setFirstName(sellerDTO.getFirstName());
        seller.setLastName(sellerDTO.getLastName());
        seller.setPassword(passwordEncoder.encode(sellerDTO.getPassword()));
        seller.setRole(sellerDTO.getRole());
        seller.setCity(city);

        sellerRepository.save(seller);
    }

    @Override
    public void delete(Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(Seller.class, "id", id.toString());
        }

        sellerRepository.delete(seller);
    }
}
