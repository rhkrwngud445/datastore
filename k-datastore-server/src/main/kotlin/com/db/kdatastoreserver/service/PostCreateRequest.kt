package com.db.kdatastoreserver.service

import com.db.kdatastoreserver.domain.Category
import org.springframework.web.multipart.MultipartFile

data class PostCreateRequest(
    val title: String,
    val content: String,
    val photos: List<MultipartFile?> = emptyList(),
    val longitude: String,
    val latitude: String,
    val price: Int,
    val category: Category,
)
