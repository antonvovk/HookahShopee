package com.wolf.hookahshopee.post.service;

import com.wolf.hookahshopee.post.dto.PostCreateDTO;
import com.wolf.hookahshopee.post.dto.PostDTO;
import com.wolf.hookahshopee.post.dto.PostLightDTO;
import com.wolf.hookahshopee.post.dto.PostUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostDTO> getAll();

    List<PostLightDTO> getAllLight();

    PostDTO findByUUID(UUID uuid);

    UUID create(PostCreateDTO dto);

    void update(PostUpdateDTO dto);

    void updateImage(UUID uuid, String imageName);

    void delete(UUID uuid);
}
