package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.SellerDTO;
import com.wolf.hookahshopee.dto.SellerLightDTO;
import com.wolf.hookahshopee.model.Role;

import java.util.List;

public interface SellerService {

    SellerDTO findById(Long id);

    SellerDTO findByPhoneNumber(String phoneNumber);

    List<SellerDTO> findAll();

    List<SellerDTO> findAllByRole(Role role);

    List<SellerDTO> findAllByCity(Long cityId);

    void create(SellerLightDTO sellerDTO);

    void update(SellerLightDTO sellerDTO, Long id);

    void delete(Long id);
}
