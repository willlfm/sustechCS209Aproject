//package com.example.analyzer.controller;
//
//import com.example.analyzer.service.StudentService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class StudentController {
//
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @RequestMapping("/list")
//    public String getStudents(Model model){
//        model.addAttribute("students", studentService.getStudents());
//        return "index";
//    }
//
//    @RequestMapping("/findByEmail")
//    public String findByEmailLike(String email, Model model){
//        model.addAttribute("students", studentService.findByEmailLike(email));
//        return "index";
//    }
//}
