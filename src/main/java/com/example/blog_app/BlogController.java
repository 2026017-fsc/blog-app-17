package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BlogController {
    @GetMapping("/")
    public String home() {
        return "/blog";
    }

    @GetMapping("/makeBlog")
    public String makeBlog() {
        return "/makeBlog";
    }
}
