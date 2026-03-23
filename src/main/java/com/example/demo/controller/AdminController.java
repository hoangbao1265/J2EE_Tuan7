package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/courses")
    public String listCourses(Model model,
                              @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseService.getAllCourses(page, 10).getContent());
        model.addAttribute("totalPages", courseService.getAllCourses(page, 10).getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categoryService.findAll());
        return "admin/courses";
    }

    @GetMapping("/courses/new")
    public String newCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/course-form";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String image,
                             @RequestParam Integer credits,
                             @RequestParam String lecturer,
                             @RequestParam(required = false) Long categoryId,
                             @RequestParam(required = false) Long courseId,
                             RedirectAttributes ra) {
        Course course;
        if (courseId != null) {
            course = courseService.findById(courseId)
                .orElse(new Course());
        } else {
            course = new Course();
        }

        course.setName(name);
        course.setImage(image);
        course.setCredits(credits);
        course.setLecturer(lecturer);

        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            course.setCategory(category);
        }

        courseService.save(course);
        ra.addFlashAttribute("success", "Lưu học phần thành công!");
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));
        model.addAttribute("course", course);
        model.addAttribute("categories", categoryService.findAll());
        return "admin/course-form";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes ra) {
        courseService.deleteById(id);
        ra.addFlashAttribute("success", "Xóa học phần thành công!");
        return "redirect:/admin/courses";
    }
}
