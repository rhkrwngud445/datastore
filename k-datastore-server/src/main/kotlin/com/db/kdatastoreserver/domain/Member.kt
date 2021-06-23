package com.db.kdatastoreserver.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "member")
class Member(
    val name: String,
    val profilePhotoUrl: String
)
