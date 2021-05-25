package com.db.datastoreserver.service.dto;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.domain.post.Location;
import com.db.datastoreserver.domain.post.Post;
import com.db.datastoreserver.domain.post.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String content;
    private Location location;
    private Long price;
    private Category category;

    public Post toEntity(Member member) {
        return Post.builder()
                .title(title)
                .author(member)
                .content(content)
                .location(location)
                .category(category)
                .price(price)
                .status(Status.SALE)
                .build();
    }
}
