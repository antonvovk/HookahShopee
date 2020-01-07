package com.wolf.hookahshopee.service;

import com.wolf.hookahshopee.dto.PostDTO;
import com.wolf.hookahshopee.dto.PostLightDTO;

import java.util.List;

public interface PostService {

    PostDTO findByName(String name);

    List<PostDTO> findAll();

    String create(PostLightDTO postDTO);

    String update(String name, PostLightDTO postDTO);

    String updateImage(String name, String imageName);

    String delete(String name);
}
