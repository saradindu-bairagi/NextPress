package com.nextpress.api.cms.post.service;

import com.nextpress.api.cms.post.entity.PostEntity;
import com.nextpress.api.cms.post.model.Post;
import com.nextpress.api.cms.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll().stream()
                .map(this::mapToPost)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        String slug = generateSlug(post.getTitle());

        PostEntity entity = PostEntity.builder()
                .title(post.getTitle())
                .slug(slug)
                .content(post.getContent())
                .status("draft")
                .authorId(post.getAuthorId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToPost(postRepository.save(entity));
    }

    private String generateSlug(String title) {
        String base = title.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        String slug = base;
        int counter = 1;

        while (postRepository.existsBySlug(slug)) {
            slug = base + "-" + counter++;
        }

        return slug;
    }

    private Post mapToPost(PostEntity entity) {
        return Post.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .slug(entity.getSlug())
                .content(entity.getContent())
                .status(entity.getStatus())
                .authorId(entity.getAuthorId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
