package com.example.springfling.Payload.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class BlogPostRequest {

    @JsonProperty("title")
    @Column(name = "title", nullable = false)
    private String title;

    @JsonProperty("textBody")
    @Column(name = "textBody", nullable = false)
    private String textBody;

    // The id of the author of the blog post
    @JsonProperty("authorId")
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    public BlogPostRequest() {}

    public Long getAuthorId() {
        return authorId;
    }

    public String getTextBody() {
        return textBody;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
