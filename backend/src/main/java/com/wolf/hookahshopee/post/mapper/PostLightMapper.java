package com.wolf.hookahshopee.post.mapper;

import com.wolf.hookahshopee.post.dto.PostLightDTO;
import com.wolf.hookahshopee.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostLightMapper {

    PostLightMapper INSTANCE = Mappers.getMapper(PostLightMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageName", target = "imageName")
    PostLightDTO toDto(Post source);

    List<PostLightDTO> toDto(List<Post> sourceList);
}
