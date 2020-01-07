package com.wolf.hookahshopee.service.impl;

import com.wolf.hookahshopee.dto.PostDTO;
import com.wolf.hookahshopee.dto.PostLightDTO;
import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.mapper.PostMapper;
import com.wolf.hookahshopee.model.Post;
import com.wolf.hookahshopee.repository.PostRepository;
import com.wolf.hookahshopee.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO findByName(String name) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        return PostMapper.INSTANCE.toDto(post);
    }

    @Override
    public List<PostDTO> findAll() {
        return PostMapper.INSTANCE.toDto(postRepository.findAll());
    }

    @Override
    public void create(PostLightDTO postDTO) {
        Post post = Post.builder()
                .name(postDTO.getName())
                .imageName(null)
                .htmlContent(postDTO.getHtmlContent())
                .build();

        postRepository.save(post);
    }

    @Override
    public void update(String name, PostLightDTO postDTO) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        post.setName(postDTO.getName());
        post.setHtmlContent(postDTO.getHtmlContent());

        postRepository.save(post);
    }

    @Override
    public void updateImage(String name, String imageName) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        post.setImageName(imageName);

        postRepository.save(post);
    }

    @Override
    public void delete(String name) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        postRepository.delete(post);
    }
}
