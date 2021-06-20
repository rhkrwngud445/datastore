package com.db.datastoreserver.domain.post;

import com.db.datastoreserver.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member author;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Photo> photos;

    @Column
    @Embedded
    private Location location;

    @Column
    private Long price;

    @Column
    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likes = new HashSet<>();

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Builder
    public Post(Long id, Member author, String title, String content,
                List<Photo> photos, Location location, Long price, Set<Like> likes,
                Category category, Status status, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        setAuthor(author);
        this.title = title;
        this.content = content;
        this.photos = photos;
        this.location = location;
        this.price = price;
        this.likes = likes;
        this.category = category;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    private void setAuthor(Member author) {
        if (Objects.isNull(this.author)) {
            this.author = author;
            this.author.addPost(this);
        }
    }
}
