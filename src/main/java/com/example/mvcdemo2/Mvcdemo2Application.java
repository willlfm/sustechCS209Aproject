package com.example.mvcdemo2;

import com.example.mvcdemo2.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Mvcdemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mvcdemo2Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentService service){
        return args -> {
            service.addStudents();
        };
    }

}
