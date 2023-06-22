package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    // do the same with native sql query, added only for reference.
    @Query(value = "SELECT * FROM posts p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    List<Post> searchPostsSql(String query);
}
