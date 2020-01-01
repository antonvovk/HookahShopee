package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.UserDTO;
import com.wolf.hookahshopee.dto.UserLightDTO;
import com.wolf.hookahshopee.model.Role;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO findByPhoneNumber(String phoneNumber);

    List<UserDTO> findAll();

    List<UserDTO> findAllByRole(Role role);

    List<UserDTO> findAllByCity(Long cityId);

    void create(UserLightDTO sellerDTO);

    void update(UserLightDTO sellerDTO, Long id);

    void delete(Long id);
}
