package com.db.kdatastoreserver.service.dto

import com.db.kdatastoreserver.domain.*
import java.time.LocalDateTime

data class PostResponse(
    val id: String,
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
) {
    constructor(entity: Post) : this(
        entity._id.toString(),
        entity.member,
        entity.title,
        entity.content,
        entity.photos,
        entity.location,
        entity.price,
        entity.likes,
        entity.category,
        entity.status,
        entity.createdDate,
        entity.updatedDate
    )
}
