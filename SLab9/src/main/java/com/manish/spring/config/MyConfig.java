package com.manish.spring.config;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@ComponentScan(basePackages = "com.manish.spring.service")
public class MyConfig {

    @Bean
    public Student stud() {
        Student st = new Student();
        st.setSid(101);
        st.setSname("Manish");
        return st;
    }

    @Bean("mystud")
    @Primary
    public Student createStudent() {
        Student st = new Student();
        st.setSid(102);
        st.setSname("Ranjan");
        return st;
    }

    @Bean
    @Primary
    public Employee emp() {
        Employee em = new Employee();
        em.setEid(1001);
        em.setEname("Kumar");
        return em;
    }

    @Bean("myemp")
    public Employee createEmployee() {
        Employee em = new Employee();
        em.setEid(1002);
        em.setEname("Kishore");
        return em;
    }

}
