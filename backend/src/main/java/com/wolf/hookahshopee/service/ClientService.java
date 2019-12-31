package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.ClientDTO;
import com.wolf.hookahshopee.dto.ClientLightDTO;

import java.util.List;

public interface ClientService {

    ClientDTO findById(Long id);

    ClientDTO findByPhoneNumber(String phoneNumber);

    List<ClientDTO> findAll();

    List<ClientDTO> findAllByCity(Long cityId);

    void create(ClientLightDTO clientDTO);

    void update(ClientLightDTO clientDTO, Long id);

    void delete(Long id);
}
