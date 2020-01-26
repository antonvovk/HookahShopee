package com.wolf.hookahshopee.post.service;

import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.post.dto.PostCreateDTO;
import com.wolf.hookahshopee.post.dto.PostDTO;
import com.wolf.hookahshopee.post.dto.PostLightDTO;
import com.wolf.hookahshopee.post.dto.PostUpdateDTO;
import com.wolf.hookahshopee.post.mapper.PostCreateMapper;
import com.wolf.hookahshopee.post.mapper.PostLightMapper;
import com.wolf.hookahshopee.post.mapper.PostMapper;
import com.wolf.hookahshopee.post.model.Post;
import com.wolf.hookahshopee.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostDTO> getAll() {
        return PostMapper.INSTANCE.toDto(postRepository.findAll());
    }

    @Override
    public List<PostLightDTO> getAllLight() {
        return PostLightMapper.INSTANCE.toDto(postRepository.findAll());
    }

    @Override
    public PostDTO findByUUID(UUID uuid) {
        Post post = postRepository.findByUuid(uuid).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "uuid", uuid.toString());
        }

        return PostMapper.INSTANCE.toDto(post);
    }

    @Override
    public UUID create(PostCreateDTO dto) {
        return postRepository.save(PostCreateMapper.INSTANCE.fromDto(dto)).getUuid();
    }

    @Override
    public void update(PostUpdateDTO dto) {
        Post post = postRepository.findByUuid(dto.getUuid()).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "uuid", dto.getUuid().toString());
        }

        post.setName(dto.getName());
        post.setHtmlContent(dto.getHtmlContent());
        postRepository.save(post);
    }

    @Override
    public void updateImage(UUID uuid, String imageName) {
        Post post = postRepository.findByUuid(uuid).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "uuid", uuid.toString());
        }

        post.setImageName(imageName);
        postRepository.save(post);
    }

    @Override
    public void delete(UUID uuid) {
        Post post = postRepository.findByUuid(uuid).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "uuid", uuid.toString());
        }

        postRepository.delete(post);
    }
}
