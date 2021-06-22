package com.db.kdatastoreserver

import com.db.kdatastoreserver.domain.Category
import com.db.kdatastoreserver.domain.Member
import com.db.kdatastoreserver.domain.Post
import com.db.kdatastoreserver.service.PostCreateRequest
import com.db.kdatastoreserver.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

const val DEFAULT_IMAGE: String = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val service: PostService
) {
    @GetMapping("/all")
    fun all(
        @RequestParam(required = false) categories: List<Category>
    ): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(service.findAllPost(categories))
    }

    @GetMapping("/{id}")
    fun each(
        @PathVariable("id") id: String
    ): ResponseEntity<Post> {
        return ResponseEntity.ok(service.findPost(id))
    }

    @PostMapping("/write")
    fun write(
        @ModelAttribute request: PostCreateRequest
    ): ResponseEntity<Void> {
        val postId = service.createPost(
            request,
            Member("김", DEFAULT_IMAGE)
        )
        return ResponseEntity.created(URI.create("/api/posts/$postId")).build()
    }
}