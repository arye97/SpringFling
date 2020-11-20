package com.example.springfling.Payload.Response;

import com.example.springfling.Model.BlogPost;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogPostResponse {

    @JsonProperty("postId")
    private Long postId;

    // The id of the author of the blog post
    @JsonProperty("authorId")
    private Long authorId;

    @JsonProperty("title")
    private String title;

    public BlogPostResponse(BlogPost postData, Long authorId) {

    }


}
