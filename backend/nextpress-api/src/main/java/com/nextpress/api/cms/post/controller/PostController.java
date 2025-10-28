package com.nextpress.api.cms.post.controller;

import com.nextpress.api.cms.post.model.Post;
import com.nextpress.api.cms.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }
}
