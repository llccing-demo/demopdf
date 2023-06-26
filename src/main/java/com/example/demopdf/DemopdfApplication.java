package com.example.demopdf;

import com.example.demopdf.entity.Student;
import com.example.demopdf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemopdfApplication implements CommandLineRunner {

    @Autowired
    public StudentService service;

    public static void main(String[] args) {
        SpringApplication.run(DemopdfApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 11; i++) {
            Student student = new Student();
            student.setName("Student " + i);
            student.setSection("Section " + i);
            service.save(student);
        }
    }

}
