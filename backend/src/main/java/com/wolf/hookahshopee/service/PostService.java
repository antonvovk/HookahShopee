package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.PostDTO;
import com.wolf.hookahshopee.dto.PostLightDTO;

import java.util.List;

public interface PostService {

    PostDTO findByName(String name);

    List<PostDTO> findAll();

    void create(PostLightDTO postDTO);

    void update(String name, PostLightDTO postDTO);

    void updateImage(String name, String imageName);

    void delete(String name);
}
