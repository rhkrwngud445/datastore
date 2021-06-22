package com.db.kdatastoreserver.service

import com.db.kdatastoreserver.domain.*
import com.db.kdatastoreserver.domain.repository.PostRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val repository: PostRepository,
    private val uploadService: UploadService
) {

    fun findPost(id: String): Post {
        return repository.findById(id)
            .orElseThrow { IllegalArgumentException() }
    }

    fun findAllPost(categories: List<Category>): List<Post> {
        return repository.findAllByCategoryIn(categories)
    }

    fun createPost(request: PostCreateRequest, member: Member): String? {
        val photoUrls = request.photos
            .map { Photo(it?.let { photo -> uploadService.upload(photo) }) }
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
        return savedPost._id.toString()
    }
}