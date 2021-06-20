package com.db.kdatastoreserver.domain.repository

import com.db.kdatastoreserver.domain.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String>