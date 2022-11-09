package com.manish.spring.service;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SecondService {

    private Student stud;
    private Employee emp;

    public SecondService(){
        System.out.println("\n## SecondService() ");
    }

    @Autowired
    public void setStud(Student stud) {
        System.out.println("\n## setStud() ");
        this.stud = stud;
    }

    @Autowired
    @Qualifier("myemp")
    public void setEmp(Employee emp) {
        System.out.println("\n## setEmp() ");
        this.emp = emp;
    }

    public void showDetails(){
        System.out.println("\n## SecondService -> showDetails() ");
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
