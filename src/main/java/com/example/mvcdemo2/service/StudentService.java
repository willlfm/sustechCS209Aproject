package com.example.mvcdemo2.service;

import com.example.mvcdemo2.model.Student;
import com.example.mvcdemo2.repository.StudentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<Student> findByEmailLike(String email){
        return studentRepository.findByEmailLike("%" + email + "%");
    }

    public void addStudents(){
        Student maria = new Student("Mary",
            "mary@gmail.com");
        Student alex = new Student("Alex",
            "alex@gmail.com");
        Student dean = new Student("Dean",
            "dean@yahoo.com");
        studentRepository.saveAll(List.of(maria, alex, dean));
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student s = studentRepository.findById(studentId).
            orElseThrow(()-> new IllegalStateException("Student ID not exists"));
        if(name!=null && name.length()>0 && !name.equals(s.getName())){
            s.setName(name);
        }
        if(email!=null && email.length()>0 && !email.equals(s.getEmail())){
            s.setEmail(email);
        }
    }
}
