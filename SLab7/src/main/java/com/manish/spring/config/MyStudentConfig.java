package com.manish.spring.config;

import com.manish.spring.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class MyStudentConfig {

        @Bean
        public Student stud(){
            Student st = new Student();
            st.setSid(101);
            st.setSname("Manish");
            return st;
        }

    @Bean("mystud")
    @Primary
    public Student createStudent(){
        Student st = new Student();
        st.setSid(102);
        st.setSname("Ranjan");
        return st;
    }

}
