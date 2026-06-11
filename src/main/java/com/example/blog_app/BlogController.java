package com.example.blog_app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog : blogs) {

            String summary = blog.getBody().replaceAll("<[^>]*>", ""); 
            if (summary.length() > 150){
                summary = summary.substring(0, 150) + "...";
            }

            blog.setSummary(summary);
        }

        model.addAttribute("blogs", blogs);

        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id,Model model) {
        model.addAttribute(
            "blog",
            blogRepository.findById(id)
        );

        return "detail";
    }

    @GetMapping("/new")
    public String newBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "makeBlog";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Blog blog) {
        blogRepository.save(blog);
        return "redirect:/";
    }
}