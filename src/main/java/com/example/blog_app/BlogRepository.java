package com.example.blog_app;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll() {
    return jdbcClient.sql("SELECT id, title, body FROM blogs ORDER BY id DESC")
    .query(Blog.class)
    .list();
  }

  public Blog findById(Long id) {
    return jdbcClient.sql("SELECT id, title, body FROM blogs WHERE id = :id")
    .param("id", id)
    .query(Blog.class)
    .single();
}

  public void save(Blog blog) {
    jdbcClient.sql("INSERT INTO blogs(title, body) VALUES(:title, :body)")
    .param("title", blog.getTitle())
    .param("body", blog.getBody())
    .update();
  }
}
