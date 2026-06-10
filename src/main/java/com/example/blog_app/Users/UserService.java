package com.example.blog_app.Users;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean authenticate(String username, String password) {
    Optional<String> hash = userRepository.findPasswordHash(username);
    if (hash.isEmpty()) {
      return false;
    }
    return passwordEncoder.matches(password, hash.get());
  }
}
