package com.db.kdatastoreserver.domain.repository

import com.db.kdatastoreserver.domain.Member
import com.db.kdatastoreserver.domain.Post
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface MemberRepository : MongoRepository<Member, String>, CustomizedMemberRepository {
    fun findByName(name: String): Member?
}

interface CustomizedMemberRepository {
    fun updateWrittenPost(member: Member, post: Post)
}

@Repository
class CustomizedMemberRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomizedMemberRepository {

    override fun updateWrittenPost(member: Member, post: Post) {

    }
}
