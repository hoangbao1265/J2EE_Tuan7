package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public boolean isAlreadyEnrolled(Student student, Course course) {
        return enrollmentRepository.existsByStudentAndCourse(student, course);
    }

    public Enrollment enroll(Student student, Course course) {
        if (isAlreadyEnrolled(student, course)) {
            throw new RuntimeException("Sinh viên đã đăng ký học phần này rồi!");
        }
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getMyEnrollments(Student student) {
        return enrollmentRepository.findByStudent(student);
    }
}
