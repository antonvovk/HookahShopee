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
    public String create(PostLightDTO postDTO) {
        Post post = Post.builder()
                .name(postDTO.getName())
                .imageName(null)
                .htmlContent(postDTO.getHtmlContent())
                .build();

        return postRepository.save(post).getName();
    }

    @Override
    public String update(String name, PostLightDTO postDTO) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        post.setName(post.getName());
        post.setHtmlContent(post.getHtmlContent());

        return postRepository.save(post).getName();
    }

    @Override
    public String updateImage(String name, String imageName) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        post.setImageName(imageName);

        return postRepository.save(post).getName();
    }

    @Override
    public String delete(String name) {
        Post post = postRepository.findByName(name).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException(Post.class, "name", name);
        }

        postRepository.delete(post);
        return name;
    }
}
