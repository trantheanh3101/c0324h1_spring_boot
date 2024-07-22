package com.codegym.c0324h1_spring_boot_2.services;

import com.codegym.c0324h1_spring_boot_2.models.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    List<Student> findAllByName(String name);
}
