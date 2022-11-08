package com.manish.spring.domain;

public class Service {
    private Student stud = null;
    private Employee emp = null;

    public Service(){
        System.out.println("\n## Service() ");
    }
    public Service(Student stud){
        System.out.println("\n## Service(Student) ");
        this.stud = stud;
    }
    public Service(Employee emp){
        System.out.println("\n## Service(Employee) ");
        this.emp = emp;
    }
    public Service(Student stud, Employee emp){
        System.out.println("\n## Service(Student, Employee) ");
        this.stud = stud;
        this.emp = emp;
    }
    public void setStud(Student stud) {
        System.out.println("## setStud() ");
        this.stud = stud;
    }
    public void setEmp(Employee emp) {
        System.out.println("## setEmp() ");
        this.emp = emp;
    }
    public void showDetails(){
        System.out.println("\n## showDetails() ");
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
