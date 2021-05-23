package com.db.datastoreserver.service.dto;

import com.db.datastoreserver.domain.post.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private MemberResponse author;
    private String title;
    private String content;
    private List<Photo> photos;
    private Location location;
    private Long price;
    private Category category;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .author(MemberResponse.of(post.getAuthor()))
                .title(post.getTitle())
                .content(post.getContent())
                .photos(post.getPhotos())
                .location(post.getLocation())
                .price(post.getPrice())
                .category(post.getCategory())
                .status(post.getStatus())
                .createdDate(post.getCreatedDate())
                .updatedDate(post.getUpdatedDate())
                .build();
    }

    public static List<PostResponse> of(List<Post> posts) {
        return posts.stream().map(PostResponse::of).collect(Collectors.toList());
    }
}
