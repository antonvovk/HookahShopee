package com.wolf.hookahshopee.post.mapper;

import com.wolf.hookahshopee.post.dto.PostUpdateDTO;
import com.wolf.hookahshopee.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostUpdateMapper {

    PostUpdateMapper INSTANCE = Mappers.getMapper(PostUpdateMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "htmlContent", target = "htmlContent")
    Post fromDto(PostUpdateDTO source);
}
