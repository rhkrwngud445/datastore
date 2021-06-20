package com.db.datastoreserver.service.dto;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.post.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String content;
    private String longitude;
    private String latitude;
    private Long price;
    private Category category;
    private List<MultipartFile> photos;

    public Post toEntity(Member member, List<Photo> photos) {
        return Post.builder()
                .title(title)
                .author(member)
                .content(content)
                .location(Location.builder().longitude(longitude).latitude(latitude).build())
                .category(category)
                .price(price)
                .status(Status.SALE)
                .photos(photos)
                .build();
    }
}
