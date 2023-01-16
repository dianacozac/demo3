package com.example.demo3.dao;

import com.example.demo3.model.Student;

import java.util.List;

public interface Dao {

    public List<Student> getAll ();

    /**
     *
     * @param firstNamePrefix and lastNamePrefix  to be used as filtered
     * @return Student list filtered by name
     */
    public  List<Student> getStudentsFiltered (String firstNamePrefix, String lastNamePrefix);

    /**
     *
     * @param fieldName to be used as sorted
     * @return Student list sorted by first name
     */
    public List<Student> getStudentsSorted (String fieldName);

}
