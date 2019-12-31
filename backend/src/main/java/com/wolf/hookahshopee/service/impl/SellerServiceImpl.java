package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.SellerDTO;
import com.wolf.hookahshopee.dto.SellerLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.SellerMapper;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.model.Seller;
import com.wolf.hookahshopee.model.Shop;
import com.wolf.hookahshopee.repository.SellerRepository;
import com.wolf.hookahshopee.repository.ShopRepository;
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

    private final ShopRepository shopRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ShopRepository shopRepository, BCryptPasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.shopRepository = shopRepository;
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
    public List<SellerDTO> findAllByShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "shopId", shopId.toString());
        }

        return SellerMapper.INSTANCE.toDto(sellerRepository.findAllByShop(shop));
    }

    @Override
    public void create(SellerLightDTO sellerDTO) {
        Shop shop = shopRepository.findById(sellerDTO.getShopId()).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "shopId", sellerDTO.getShopId().toString());
        }

        Seller seller = Seller.builder()
                .phoneNumber(sellerDTO.getPhoneNumber())
                .firstName(sellerDTO.getFirstName())
                .lastName(sellerDTO.getLastName())
                .password(passwordEncoder.encode(sellerDTO.getPassword()))
                .role(sellerDTO.getRole())
                .shop(shop)
                .build();

        sellerRepository.save(seller);
    }

    @Override
    public void update(SellerLightDTO sellerDTO, Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);

        if (seller == null) {
            throw new EntityNotFoundException(Seller.class, "id", id.toString());
        }

        Shop shop = shopRepository.findById(sellerDTO.getShopId()).orElse(null);

        if (shop == null) {
            throw new EntityNotFoundException(Shop.class, "shopId", sellerDTO.getShopId().toString());
        }

        seller.setPhoneNumber(sellerDTO.getPhoneNumber());
        seller.setFirstName(sellerDTO.getFirstName());
        seller.setLastName(sellerDTO.getLastName());
        seller.setPassword(passwordEncoder.encode(sellerDTO.getPassword()));
        seller.setRole(sellerDTO.getRole());
        seller.setShop(shop);

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
