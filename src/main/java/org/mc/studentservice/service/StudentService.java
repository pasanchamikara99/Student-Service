package org.mc.studentservice.service;

import lombok.AllArgsConstructor;
import org.mc.studentservice.dto.School;
import org.mc.studentservice.dto.StudentDto;
import org.mc.studentservice.dto.StudentResponse;
import org.mc.studentservice.model.Student;
import org.mc.studentservice.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service

public class StudentService {


    private final StudentRepository studentRepository;


    private final RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> createStudent(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .gender(studentDto.getGender())
                .schoolId(studentDto.getSchoolId())
                .build();
        try {
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/school/" + student.get().getSchoolId(), School.class);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Student Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents() {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty()) {
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Students", HttpStatus.NOT_FOUND);
        }
    }
}
