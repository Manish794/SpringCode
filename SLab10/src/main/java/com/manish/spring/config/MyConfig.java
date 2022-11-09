package com.manish.spring.config;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

@ComponentScan(basePackages = "com.manish.spring.service")
public class MyConfig {

    @Bean
    public Student stud() {
        Student st = new Student();
        st.setSid(101);
        st.setSname("Manish");
        return st;
    }

    @Bean
    @Lazy // Creates the Object only if used
    public Employee emp() {
        return new Employee();
    }

}
