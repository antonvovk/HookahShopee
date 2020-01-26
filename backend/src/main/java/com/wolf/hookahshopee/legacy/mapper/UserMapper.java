package com.wolf.hookahshopee.legacy.mapper;

import com.wolf.hookahshopee.legacy.dto.UserDTO;
import com.wolf.hookahshopee.legacy.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends IMapper<User, UserDTO> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
