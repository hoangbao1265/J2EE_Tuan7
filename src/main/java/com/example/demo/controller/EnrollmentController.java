package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/{courseId}")
    public String enroll(@PathVariable Long courseId,
                         Authentication auth,
                         RedirectAttributes ra) {
        Student student = studentService.findByUsername(auth.getName());
        Course course = courseService.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Học phần không tồn tại"));

        try {
            enrollmentService.enroll(student, course);
            ra.addFlashAttribute("success", "Đăng ký học phần \"" + course.getName() + "\" thành công!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/home";
    }

    @GetMapping("/my-courses")
    public String myCourses(Authentication auth, Model model) {
        Student student = studentService.findByUsername(auth.getName());
        List<Enrollment> enrollments = enrollmentService.getMyEnrollments(student);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("student", student);
        return "my-courses";
    }
}
