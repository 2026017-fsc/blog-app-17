package com.example.blog_app;

public class Blog {
    private String title;
    private String content;

    public void setContent(String text) {
        this.content = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
