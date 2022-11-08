package com.manish.spring.config;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Service;
import com.manish.spring.domain.Student;
import org.springframework.context.annotation.Bean;

public class MyServiceConfig {

        @Bean
        public Service serv1(Student st, Employee em){
            Service s =  new Service();
            s.setStud(st);
            s.setEmp(em);
            return s;
        }

    @Bean("serv2")
    public Service createService2(Student st, Employee em){
        Service s =  new Service(st);
        s.setEmp(em);
        return s;
    }

    @Bean("serv3")
    public Service createService3(Student st, Employee em){
        Service s =  new Service(em);
        s.setStud(st);
        return s;
    }

    @Bean("serv4")
    public Service createService4(Student st, Employee em){
        Service s =  new Service(st, em);
        return s;
    }

    @Bean("serv5")
    public Service createService5(Student st, Employee em){
        return new Service();
    }

}
