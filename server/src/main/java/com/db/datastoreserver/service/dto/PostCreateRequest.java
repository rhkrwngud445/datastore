package com.db.datastoreserver.service.dto;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.post.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public Post toEntity(Member member, List<Photo> photos) {
        return Post.builder()
                .title(title)
                .author(member)
                .content(content)
                .location(location)
                .category(category)
                .price(price)
                .status(Status.SALE)
                .photos(photos)
                .build();
    }
}
