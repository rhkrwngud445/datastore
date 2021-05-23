package com.db.datastoreserver.service;

import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.domain.post.repository.PostRepository;
import com.db.datastoreserver.service.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponse> findAllPosts() {
        return PostResponse.of(postRepository.findAll());
    }

    public List<PostResponse> findAllPosts(List<Category> categories) {
        if (Objects.isNull(categories) || categories.isEmpty()) {
            return findAllPosts();
        }

        return categories.stream()
                .map(postRepository::findAllByCategory)
                .flatMap(List::stream)
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }
}
