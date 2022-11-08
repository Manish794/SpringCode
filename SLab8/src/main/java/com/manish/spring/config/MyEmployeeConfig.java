package com.manish.spring.config;

import com.manish.spring.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class MyEmployeeConfig {
    @Bean
    @Primary
    public Employee emp(){
        Employee em = new Employee();
        em.setEid(1001);
        em.setEname("Kumar");
        return em;
    }

    @Bean("myemp")
    public Employee createEmployee(){
        Employee em = new Employee();
        em.setEid(1002);
        em.setEname("Kishore");
        return em;
    }

}
