package com.wolf.hookahshopee.user.service;

import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.city.repository.CityRepository;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.user.dto.UserDTO;
import com.wolf.hookahshopee.user.dto.UserLightDTO;
import com.wolf.hookahshopee.user.mapper.UserMapper;
import com.wolf.hookahshopee.user.model.Role;
import com.wolf.hookahshopee.user.model.User;
import com.wolf.hookahshopee.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CityRepository cityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "phoneNumber", phoneNumber);
        }

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return UserMapper.INSTANCE.toDto(userRepository.findAll());
    }

    @Override
    public List<UserDTO> findAllSellers() {
        return UserMapper.INSTANCE.toDto(userRepository.findAllByRoleIn(Arrays.asList(Role.ADMIN, Role.SELLER)));
    }

    @Override
    public List<UserDTO> findAllByRole(Role role) {
        return UserMapper.INSTANCE.toDto(userRepository.findAllByRole(role));
    }

    @Override
    public List<UserDTO> findAllByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityId", cityId.toString());
        }

        return UserMapper.INSTANCE.toDto(userRepository.findAllByCity(city));
    }

    @Override
    public void register(UserLightDTO sellerDTO, Role role) {
        City city = cityRepository.findByUuid(sellerDTO.getCityUUID()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", sellerDTO.getCityUUID().toString());
        }

        User user = User.builder()
                .phoneNumber(sellerDTO.getPhoneNumber())
                .firstName(sellerDTO.getFirstName())
                .lastName(sellerDTO.getLastName())
                .password(passwordEncoder.encode(sellerDTO.getPassword()))
                .role(role)
                .city(city)
                .build();

        userRepository.save(user);
    }

    @Override
    public void update(UserLightDTO sellerDTO, Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        City city = cityRepository.findByUuid(sellerDTO.getCityUUID()).orElse(null);

        if (city == null) {
            throw new EntityNotFoundException(City.class, "cityUUID", sellerDTO.getCityUUID().toString());
        }

        user.setPhoneNumber(sellerDTO.getPhoneNumber());
        user.setFirstName(sellerDTO.getFirstName());
        user.setLastName(sellerDTO.getLastName());
        user.setPassword(passwordEncoder.encode(sellerDTO.getPassword()));
        user.setCity(city);

        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        userRepository.delete(user);
    }
}
