package com.example.springfling.Payload.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class BlogPostRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("textBody")
    private String textBody;

    public BlogPostRequest() {}

    public String getTextBody() {
        return textBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
