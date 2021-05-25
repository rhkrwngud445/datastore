package com.db.datastoreserver.service;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.domain.post.Post;
import com.db.datastoreserver.domain.post.repository.PostRepository;
import com.db.datastoreserver.service.dto.PostCreateRequest;
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

    public PostResponse findPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return PostResponse.of(post);
    }

    public Long createPost(PostCreateRequest request, Member member) {
        Post savedPost = postRepository.save(request.toEntity(member));

        return savedPost.getId();
    }
}
