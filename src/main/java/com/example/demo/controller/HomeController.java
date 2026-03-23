package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping({"/", "/home"})
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword,
            Model model,
            Authentication auth) {

        Page<Course> coursePage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            coursePage = courseService.searchCourses(keyword.trim(), page, 5);
        } else {
            coursePage = courseService.getAllCourses(page, 5);
        }

        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());
        model.addAttribute("keyword", keyword);

        // Lấy danh sách đã đăng ký nếu là STUDENT
        if (auth != null && auth.isAuthenticated()) {
            Student student = studentService.findByUsername(auth.getName());
            if (student != null) {
                List<Enrollment> enrollments = enrollmentService.getMyEnrollments(student);
                Set<Long> enrolledCourseIds = new HashSet<>();
                for (Enrollment e : enrollments) {
                    enrolledCourseIds.add(e.getCourse().getId());
                }
                model.addAttribute("enrolledCourseIds", enrolledCourseIds);
            }
        }

        return "home";
    }

    @GetMapping("/courses")
    public String courses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword,
            Model model,
            Authentication auth) {
        return home(page, keyword, model, auth);
    }
}
