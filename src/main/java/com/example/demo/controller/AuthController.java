package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        if (logout != null) {
            model.addAttribute("logout", "Bạn đã đăng xuất thành công!");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           Model model,
                           RedirectAttributes ra) {
        // Validate
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Username không được để trống!");
            return "register";
        }
        if (password == null || password.length() < 6) {
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự!");
            return "register";
        }
        if (email == null || !email.contains("@")) {
            model.addAttribute("error", "Email không hợp lệ!");
            return "register";
        }
        if (studentService.existsByUsername(username.trim())) {
            model.addAttribute("error", "Username đã tồn tại!");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "register";
        }
        if (studentService.existsByEmail(email.trim())) {
            model.addAttribute("error", "Email đã được sử dụng!");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "register";
        }

        studentService.register(username.trim(), password, email.trim());
        ra.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/login";
    }
}
