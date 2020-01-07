package com.wolf.hookahshopee.mapper;

import com.wolf.hookahshopee.dto.PostDTO;
import com.wolf.hookahshopee.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper extends IMapper<Post, PostDTO> {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
}
