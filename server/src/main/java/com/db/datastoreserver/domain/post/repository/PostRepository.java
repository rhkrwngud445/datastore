package com.db.datastoreserver.domain.post.repository;

import com.db.datastoreserver.domain.post.Category;
import com.db.datastoreserver.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategory(Category category);
}
