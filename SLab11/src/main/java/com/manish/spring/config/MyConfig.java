package com.manish.spring.config;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@ComponentScan(basePackages = "com.manish.spring.service")
public class MyConfig {

    @Bean   // Not Lazy & It is Singleton Scope (Only one object will be available for entire application)
    public Student stud() {
        Student st = new Student();
        st.setSid(101);
        st.setSname("Manish");
        return st;
    }

    @Bean
    @Lazy // Creates the Object only if used
    @Scope("prototype") // Every time a new object will be created and returned
    public Employee emp() {
        return new Employee();
    }

}
