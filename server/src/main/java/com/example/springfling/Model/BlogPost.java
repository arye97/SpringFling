package com.example.springfling.Model;

import com.example.springfling.Payload.Request.BlogPostRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogpost")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    @JsonProperty("id")
    private Long postId;

    // The timestamp of the feed event generation - ie. when the feed post was triggered
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("timeStamp")
    @Column(name = "time_stamp", nullable = false, columnDefinition = "DATETIME")
    private Date timeStamp;

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

    public BlogPost() {}

    public BlogPost(Long authorId, String title, String textBody) {
        setTimeStampNow();
        this.authorId = authorId;
        this.title = title;
        this.textBody = textBody;
    }

    public BlogPost(BlogPostRequest blogPostRequest, Long authorId) {
        setTimeStampNow();
        this.authorId = authorId;
        this.title = blogPostRequest.getTitle();
        this.textBody = blogPostRequest.getTextBody();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId() {
        this.authorId = authorId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId() {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody() {
        this.textBody = textBody;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date time) {
        timeStamp = time;
    }

    public void setTimeStampNow() {
        timeStamp = new Date();
    }

}
