package com.example.springfling.Repository;

import com.example.springfling.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@RepositoryRestResource
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findAll();
    List<BlogPost> findAllByAuthorId(Long authorId);
    BlogPost findByPostId(Long postId);
}