package com.wolf.hookahshopee.post.mapper;

import com.wolf.hookahshopee.post.dto.PostDTO;
import com.wolf.hookahshopee.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageName", target = "imageName")
    @Mapping(source = "htmlContent", target = "htmlContent")
    PostDTO toDto(Post source);

    List<PostDTO> toDto(List<Post> sourceList);
}
