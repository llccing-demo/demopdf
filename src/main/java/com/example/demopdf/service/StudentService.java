package com.example.demopdf.service;

import com.example.demopdf.entity.Student;
import com.example.demopdf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> findAllStudents() {
        return repo.findAll();
    }

    public void save(Student student) {
        repo.save(student);
    }
}
