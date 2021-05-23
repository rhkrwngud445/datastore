package com.db.datastoreserver.controller;

import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.service.PostService;
import com.db.datastoreserver.service.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> all(
            @RequestParam(required = false) List<Category> categories) {
        return ResponseEntity.ok(postService.findAllPosts(categories));
    }
}
