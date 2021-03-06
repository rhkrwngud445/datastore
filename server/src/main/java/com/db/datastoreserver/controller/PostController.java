package com.db.datastoreserver.controller;

import com.db.datastoreserver.domain.member.repository.MemberRepository;
import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.service.PostService;
import com.db.datastoreserver.service.dto.PostCreateRequest;
import com.db.datastoreserver.service.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    private final MemberRepository session;

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> all(
            @RequestParam(required = false) List<Category> categories) {
        return ResponseEntity.ok(postService.findAllPosts(categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> each(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(postService.findPost(id));
    }

    @PostMapping(value = "/write", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> write(
            @ModelAttribute PostCreateRequest request
    ) throws IOException {
        Long postId = postService.createPost(request, session.findById(1L)
                .orElseThrow(IllegalArgumentException::new));

        return ResponseEntity.created(URI.create("/api/posts/" + postId)).build();
    }
}
