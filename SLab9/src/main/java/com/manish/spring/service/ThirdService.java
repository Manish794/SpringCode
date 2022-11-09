package com.manish.spring.service;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ThirdService {

    private Student stud;
    private Employee emp;

    @Autowired
    public ThirdService(Student stud,@Qualifier ("myemp") Employee emp){
        System.out.println("\n## ThirdService(Student,Employee) ");
        this.stud = stud;
        this.emp = emp;
    }

    public void showDetails(){
        System.out.println("\n## ThirdService -> showDetails() ");
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
