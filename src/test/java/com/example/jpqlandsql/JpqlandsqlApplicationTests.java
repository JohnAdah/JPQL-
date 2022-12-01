package com.example.jpqlandsql;

import com.example.jpqlandsql.Repos.NullException;
import com.example.jpqlandsql.Repos.StudentRepository;
import com.example.jpqlandsql.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JpqlandsqlApplicationTests {

    @Autowired
    StudentRepository studentRepo;
    NullException nullException;

    @Test
    void contextLoads() {
    }

    @Test
    public void createStudentTest(){
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Adekunle");
        student.setScore(234);

        Student student1 = new Student();
        student1.setFirstName("Peter");
        student1.setLastName("Rodoff");
        student1.setScore(230);

        studentRepo.save(student);
        studentRepo.save(student1);

    }

    @Test
    public void updateStudentTest(){
        Student stud = studentRepo.findById(5L).get();
        System.out.println(stud);
        assertNotNull(stud);
        stud.setScore(202);
        studentRepo.save(stud);
        System.out.println(stud);
    }

    @Test
    public void selectAllStudents(){
        List<Student> students = studentRepo.findAllStudent();
        System.out.println(students);
    }

    @Test
    public void selectPartialData(){
        List<Object[]> students = studentRepo.findAllStudentsPartialData();
        students.forEach(p -> System.out.println(p[0] + " " + p[1]));
    }

    @Test
    public void findAllStudentByFirstNameTest(){
        List<Student> students = studentRepo.findAllStudentsByFirstName("Peter");
        System.out.println(students);
    }

    @Test
    public void findByScoresTest(){
        List<Student> studs = studentRepo.findAllStudentsByScoreIsBetween(220,200);
        studs.forEach(p -> System.out.println(p.toString()));

    }

    @Test
    @Transactional // This notifies spring boot that DML operations is carried on on the said method.
    @Rollback(value = false) // Stop the DML query from being rolled back.
    public void deleteStudentByFirstname(){
        studentRepo.deleteAllByFirstName("John");

    }



}
