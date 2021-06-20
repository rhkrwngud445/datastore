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

    fun findAllPost(categories: List<Category>): List<Post> {
        return repository.findAll()
    }

    fun createPost(request: PostCreateRequest, member: Member): Long? {
        val photoUrls = request.photos
            .map { Photo(it?.let { photo -> uploadService.upload(photo) }) }
            .toList()

        repository.save(
            Post(
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
        return 1
    }
}