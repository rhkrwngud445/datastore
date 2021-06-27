package com.db.kdatastoreserver.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "member")
data class Member(
    @Id
    val _id: ObjectId?,
    val name: String,
    val profilePhotoUrl: String,
    //TODO : ignore Post.member.posts (spring mongo nested field exclude)
    val posts: MutableList<String>
) {
    constructor(name: String, profilePhotoUrl: String) :
            this(null, name, profilePhotoUrl, ArrayList())

    constructor(id: String, name: String, profilePhotoUrl: String) :
            this(ObjectId(id), name, profilePhotoUrl, ArrayList())

    fun addPost(post: Post) {
        posts.add(post._id.toString())
    }
}
