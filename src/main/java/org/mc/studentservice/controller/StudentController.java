package org.mc.studentservice.controller;


import org.mc.studentservice.dto.StudentDto;
import org.mc.studentservice.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable int id) {
        return studentService.fetchStudentById(id);
    }

    @GetMapping
    public ResponseEntity<?> fetchStudents() {
        return studentService.fetchStudents();
    }

    @PostMapping("save")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto student) {
        return studentService.createStudent(student);
    }
}
