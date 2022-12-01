package com.example.jpqlandsql.Repos;

import com.example.jpqlandsql.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    //JPQueryL to retrieve all the list of students in the database
    @Query("from Student") // the JPQ Code to probe the database
    List<Student> findAllStudent();

    //PARTIAL Record retrieval returns an object array.
    @Query("select stud.firstName, stud.lastName from Student stud")
    List<Object[]> findAllStudentsPartialData();

    //Querying with named parameters
    @Query("from Student where firstName = :firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("from Student where score BETWEEN :max1  AND :max2")
    List<Student> findAllStudentsByScoreIsBetween(@Param("max2") int max, @Param("max1") int min);

    @Modifying // This helps springboot understand the query should modify the database
    @Query("delete from Student where firstName = :firstName")
    void deleteAllByFirstName(@Param("firstName") String name);
}
