package com.example.springfling.Controller;

import com.example.springfling.Model.BlogPost;
import com.example.springfling.Model.User;
import com.example.springfling.Payload.Request.BlogPostRequest;
import com.example.springfling.Payload.Response.BlogPostResponse;
import com.example.springfling.Repository.BlogPostRepository;
import com.example.springfling.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BlogPostController {

    private final UserRepository userRepository;
    private final BlogPostRepository blogPostRepository;

    public BlogPostController(UserRepository userRepository, BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/createpost/{authorId}")
    public BlogPostResponse createBlogPost(@PathVariable Long authorId, @Validated @RequestBody BlogPostRequest blogPostData,
                                           HttpServletResponse response) {

        BlogPost newBlogPost = new BlogPost(blogPostData, authorId);
        blogPostRepository.save(newBlogPost);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return new BlogPostResponse(newBlogPost, authorId);

    }

    @GetMapping("/blogpost/{postId}")
    public BlogPost getBlogPost(@PathVariable Long postId) {
        BlogPost blogPost = blogPostRepository.findByPostId(postId);
        if (blogPost == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with ID = " + postId + " does not exist");
        }
        return blogPost;
    }

    @GetMapping("/user/blogpost/{userId}")
    public List<BlogPost> getAllUsersBlogPosts(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
        }
        List<BlogPost> allPosts = blogPostRepository.findAllByAuthorId(userId);
        if (allPosts == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no blog posts");
        }
        return allPosts;
    }
}
