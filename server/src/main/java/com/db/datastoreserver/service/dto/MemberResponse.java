package com.db.datastoreserver.service.dto;

import com.db.datastoreserver.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private String profilePhotoUrl;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .profilePhotoUrl(member.getProfilePhotoUrl())
                .build();
    }
}
