package com.db.datastoreserver.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Like(Long id, Long memberId, Post post) {
        this.id = id;
        this.memberId = memberId;
        setPost(post);
    }

    private void setPost(Post post) {
        if (Objects.isNull(this.post)) {
            this.post = post;
            this.post.getLikes().add(this);
        }
    }
}
