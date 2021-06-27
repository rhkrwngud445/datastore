package com.db.kdatastoreserver.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Reference
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "post")
data class Post(
    val _id: ObjectId?,
    @Reference
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

data class Location(
    val longitude: String,
    val latitude: String
)

enum class Category {
    디지털_가전,
    가구_인테리어;
}

enum class Status {
    SALE,
    RESERVE,
    SOLD;
}
