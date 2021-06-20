package com.db.datastoreserver.domain;

import com.db.datastoreserver.domain.member.Member;
import com.db.datastoreserver.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    public void saveTest() {
        Member member = Member.builder()
                .name("사람")
                .profilePhotoUrl("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200")
                .build();

        assertDoesNotThrow(() -> repository.save(member));
    }

    @Test
    public void findTest() {
        Member member = Member.builder()
                .name("사람")
                .profilePhotoUrl("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200")
                .build();

        Member savedMember = repository.save(member);
        Member findMember = repository.findById(savedMember.getId()).orElse(null);

        assertThat(savedMember).isEqualTo(findMember);
    }
}
