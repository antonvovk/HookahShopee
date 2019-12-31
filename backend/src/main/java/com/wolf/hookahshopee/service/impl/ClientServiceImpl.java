package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.ClientDTO;
import com.wolf.hookahshopee.dto.ClientLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.ClientMapper;
import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Client;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.repository.CityRepository;
import com.wolf.hookahshopee.repository.ClientRepository;
import com.wolf.hookahshopee.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final CityRepository cityRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, CityRepository cityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.cityRepository = cityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(Client.class, "id", id.toString());
        }

        return ClientMapper.INSTANCE.toDto(client);
    }

    @Override
    public ClientDTO findByPhoneNumber(String phoneNumber) {
        Client client = clientRepository.findByPhoneNumber(phoneNumber).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(Client.class, "phoneNumber", phoneNumber);
        }

        return ClientMapper.INSTANCE.toDto(client);
    }

    @Override
    public List<ClientDTO> findAll() {
        return ClientMapper.INSTANCE.toDto(clientRepository.findAll());
    }

    @Override
    public List<ClientDTO> findAllByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", cityId.toString());
        }

        return ClientMapper.INSTANCE.toDto(clientRepository.findAllByCity(city));
    }

    @Override
    public void create(ClientLightDTO clientDTO) {
        City city = cityRepository.findById(clientDTO.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", clientDTO.getCityId().toString());
        }

        Client client = Client.builder()
                .phoneNumber(clientDTO.getPhoneNumber())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .password(passwordEncoder.encode(clientDTO.getPassword()))
                .role(Role.CLIENT)
                .city(city)
                .build();

        clientRepository.save(client);
    }

    @Override
    public void update(ClientLightDTO clientDTO, Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(Client.class, "id", id.toString());
        }

        City city = cityRepository.findById(clientDTO.getCityId()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", clientDTO.getCityId().toString());
        }

        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setCity(city);

        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new EntityNotFoundException(Client.class, "id", id.toString());
        }

        clientRepository.delete(client);
    }
}
