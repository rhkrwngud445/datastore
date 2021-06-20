package com.db.datastoreserver.domain;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.member.repository.MemberRepository;
import com.db.datastoreserver.domain.post.*;
import com.db.datastoreserver.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
class PostRepositoryTest {

    private static final String RANDOM_PHOTO_URL = "https://picsum.photos/200";
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        Member member = Member.builder()
                .name("사람")
                .profilePhotoUrl("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200")
                .build();

        memberRepository.save(member);

        Location location = Location.builder()
                .longitude("35.191273")
                .latitude("129.096987")
                .build();

        Post post = Post.builder()
                .title("닌텐도팔아요~")
                .author(member)
                .category(Category.디지털_가전)
                .content("네고사절")
                .price(350_000L)
                .status(Status.SALE)
                .location(location)
                .build();

        assertDoesNotThrow(() -> postRepository.save(post));
    }

    @Test
    public void findTest() {
        Member member = Member.builder()
                .name("사람")
                .profilePhotoUrl("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200")
                .build();

        memberRepository.save(member);

        Location location = Location.builder()
                .longitude("35.191273")
                .latitude("129.096987")
                .build();

        List<Photo> photos = Arrays.asList(Photo.builder()
                .url(RANDOM_PHOTO_URL).build(), Photo.builder().url(RANDOM_PHOTO_URL)
                .build());

        Post post = Post.builder()
                .title("닌텐도팔아요~")
                .author(member)
                .category(Category.디지털_가전)
                .content("네고사절")
                .price(350_000L)
                .status(Status.SALE)
                .location(location)
                .photos(photos)
                .build();

        Post savedPost = postRepository.save(post);
        Post findPost = postRepository.findById(savedPost.getId()).orElse(null);

        assertThat(savedPost).isEqualTo(findPost);
    }
}
