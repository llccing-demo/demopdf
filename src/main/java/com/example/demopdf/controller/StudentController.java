package com.example.demopdf.controller;

import com.example.demopdf.entity.Student;
import com.example.demopdf.service.StudentService;
import com.example.demopdf.util.PDFGenerator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("pdf/students")
    public void generatedPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Student> studentList = service.findAllStudents();

        PDFGenerator generator = new PDFGenerator();
        generator.setStudentList(studentList);
        generator.generate(response);
    }

    @GetMapping("pdf/txt")
    public void generateByTxt() {
        PDFGenerator generator = new PDFGenerator();
        generator.generateByTxt();
    }
}
