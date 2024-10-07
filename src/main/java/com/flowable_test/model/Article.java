package com.flowable_test.model;

public class Article {

    private String id;
    private String author;
    private String url;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public Article(String id, String author, String url) {
        this.id = id;
        this.author = author;
        this.url = url;
    }
}
