package ru.inmylife.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.inmylife.blog.service.SessionService;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final SessionService sessionService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("isAuth", sessionService.isAuthenticated());
        model.addAttribute("topics", userService.getUserTopics());

        return "public/login";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        sessionService.logout(request, response, authentication);
        return new RedirectView("/");
    }
}
