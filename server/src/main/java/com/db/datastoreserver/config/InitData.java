package com.db.datastoreserver.config;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.member.repository.MemberRepository;
import com.db.datastoreserver.domain.post.*;
import com.db.datastoreserver.domain.post.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitData {
    private static final String RANDOM_PHOTO_URL = "https://picsum.photos/200";

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository, PostRepository postRepository) {
        return args -> {
            Member member = memberRepository.save(Member.builder()
                    .name("사람")
                    .profilePhotoUrl("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200")
                    .build());

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

            List<Photo> photos2 = Arrays.asList(Photo.builder()
                    .url(RANDOM_PHOTO_URL).build(), Photo.builder().url(RANDOM_PHOTO_URL)
                    .build());

            Post post2 = Post.builder()
                    .title("의자 팔아요")
                    .author(member)
                    .category(Category.가구_인테리어)
                    .content("네고사절")
                    .price(18_000L)
                    .status(Status.SALE)
                    .location(location)
                    .photos(photos2)
                    .build();

            postRepository.save(post);
            postRepository.save(post2);
        };
    }
}
