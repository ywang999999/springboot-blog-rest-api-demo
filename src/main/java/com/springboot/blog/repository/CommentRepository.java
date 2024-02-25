package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //This method signature declares a query method.
    // It's a custom query method provided by Spring Data JPA based on the method name.
    // This method retrieves a list of comments based on the postId.
    // It's using the naming convention of Spring Data JPA
    List<Comment> findByPostId(long postId);
}

