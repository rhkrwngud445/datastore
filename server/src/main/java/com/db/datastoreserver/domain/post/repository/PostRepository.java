package com.db.datastoreserver.domain.post.repository;

import com.db.datastoreserver.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
