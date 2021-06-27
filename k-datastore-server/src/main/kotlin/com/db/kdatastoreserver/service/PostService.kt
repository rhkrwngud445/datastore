package com.db.kdatastoreserver.service

import com.db.kdatastoreserver.domain.*
import com.db.kdatastoreserver.domain.repository.PostRepository
import com.db.kdatastoreserver.service.dto.PostCreateRequest
import com.db.kdatastoreserver.service.dto.PostResponse
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val repository: PostRepository,
    private val memberService: MemberService,
    private val s3Service: S3Service
) {

    fun findPost(id: String): PostResponse {
        return repository.findById(id)
            .map { PostResponse(it) }
            .orElseThrow { IllegalArgumentException() }
    }

    fun findAllPost(categories: List<Category>): List<PostResponse> {
        return repository.findAllByCategoryIn(categories)
            .map { PostResponse(it) }
    }

    fun createPost(request: PostCreateRequest, member: Member): String? {
        val photoUrls = request.photos
            .map { s3Service.upload(it) }
            .map { s3Service.retrieve(it) }
            .map { Photo(it) }
            .toList()

        val savedPost = repository.save(
            Post(
                null,
                member,
                request.title,
                request.content,
                photoUrls,
                Location(request.longitude, request.latitude),
                request.price,
                emptyList(),
                request.category,
                Status.SALE,
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        )

        memberService.addWrittenPost(member, savedPost)
        return savedPost._id.toString()
    }
}
