package com.example.blog_app;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.blog_app.Users.LoginForm;
import com.example.blog_app.Users.UserService;




@Controller
public class BlogController {
    @GetMapping("/")
    public String home() {
        return "/blog";
    }

    @GetMapping("/makeBlog")
    public String makeBlog(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("username") == null) {
            redirectAttributes.addFlashAttribute("message", "ログインしてください");
            return "redirect:/login";
        }
        return "/makeBlog";
    }

    private final UserService userService;

    public BlogController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpSession session,
        RedirectAttributes redirectAttributes) {
        if (userService.authenticate(form.getUsername(), form.getPassword())) {
        session.setAttribute("username", form.getUsername());
        redirectAttributes.addFlashAttribute("message", "ログインしました");
        return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("message", "ユーザー名かパスワードが違います");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @ModelAttribute
    public void addLoginInfo(HttpSession session, Model model) {
        model.addAttribute(
            "loginUser",
            session.getAttribute("username")
        );
    }
}
