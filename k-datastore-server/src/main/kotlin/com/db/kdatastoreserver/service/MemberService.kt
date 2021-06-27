package com.db.kdatastoreserver.service

import com.db.kdatastoreserver.domain.Member
import com.db.kdatastoreserver.domain.Post
import com.db.kdatastoreserver.domain.repository.MemberRepository
import org.springframework.stereotype.Service


@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun addWrittenPost(member: Member, post: Post) {
        member.addPost(post)
        memberRepository.save(member)
    }
}