package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.ClientDTO;
import com.wolf.hookahshopee.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper extends IMapper<Client, ClientDTO> {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
