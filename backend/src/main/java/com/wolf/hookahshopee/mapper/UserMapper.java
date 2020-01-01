package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.UserDTO;
import com.wolf.hookahshopee.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends IMapper<User, UserDTO> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
