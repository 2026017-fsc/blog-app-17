package com.example.blog_app.Users;

import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private final JdbcClient jdbcClient;

  public UserRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public Optional<String> findPasswordHash(String username) {
    return jdbcClient.sql("SELECT password_hash FROM users WHERE username = :username")
        .param("username", username)
        .query(String.class)
        .optional();
  }
}
