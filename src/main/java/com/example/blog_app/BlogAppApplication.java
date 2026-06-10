package com.example.blog_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// 
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

}
