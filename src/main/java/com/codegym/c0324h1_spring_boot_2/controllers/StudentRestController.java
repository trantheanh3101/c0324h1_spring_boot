package com.codegym.c0324h1_spring_boot_2.controllers;

import com.codegym.c0324h1_spring_boot_2.models.Student;
import com.codegym.c0324h1_spring_boot_2.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setScore(student.getScore());
            existingStudent.setClassroom(student.getClassroom());
            studentService.save(existingStudent);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent != null) {
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
