package com.db.kdatastoreserver.service

import com.db.kdatastoreserver.domain.*
import java.time.LocalDateTime

data class PostResponse(
    val member: Member,
    val title: String,
    val content: String,
    val photos: List<Photo>,
    val location: Location,
    val price: Int,
    val likes: List<Like>,
    val category: Category,
    val status: Status,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime,
)
