package com.manish.spring.service;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import com.manish.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

    @Autowired  // Field Injection
    private Student stud;

    @Autowired  // Field Injection
    private Employee emp;

    @Autowired(required = false)    // Optional
    private User user;

    public FirstService(){
        System.out.println("\n## FirstService() ");
    }

    public void showDetails(){
        System.out.println("\n## FirstService -> showDetails() ");
        System.out.println("Stud "+ stud);
        System.out.println("Emp "+ emp);
        if(null != stud){
            stud.showStudentDetails();
        }
        if(null != emp){
            emp.showEmployeeDetails();
        }
    }
}
