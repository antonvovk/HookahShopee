package com.wolf.hookahshopee.post.mapper;

import com.wolf.hookahshopee.post.dto.PostCreateDTO;
import com.wolf.hookahshopee.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostCreateMapper {

    PostCreateMapper INSTANCE = Mappers.getMapper(PostCreateMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "htmlContent", target = "htmlContent")
    Post fromDto(PostCreateDTO source);
}
