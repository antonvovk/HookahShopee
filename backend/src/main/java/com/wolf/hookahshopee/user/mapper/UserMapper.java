package com.wolf.hookahshopee.user.mapper;

import com.wolf.hookahshopee.user.dto.UserDTO;
import com.wolf.hookahshopee.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User source);

    List<UserDTO> toDto(List<User> sourceList);
}
