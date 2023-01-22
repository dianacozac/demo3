package com.example.demo3.web;

import com.example.demo3.dao.Dao;
import com.example.demo3.model.Student;
import com.example.demo3.service.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")

public class StudentController {

    @Autowired  // Spring injection
    private Dao studentDao;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentDao.getAll();
    }

    @GetMapping
    public List<Student> getByName(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName){
        return studentDao.getStudentsFiltered(firstName, lastName);
    }

//    @GetMapping("/sort")
//    public List<Student> getSortedStudents(@RequestParam(required = false) String firstName){
//        return studentDao.getStudentsSorted(firstName);
//
//    }

    @GetMapping("/sort")
    public List<Student> getStudentsSorted(@RequestParam(required = false) String fieldName){
        return studentDao.getStudentsSorted(fieldName);
    }

//    @GetMapping
//    public List<Student> getCompanyNameSorted(@RequestParam(required = false) String companyName){
//        return studentDao.getCompanyNameSorted(companyName);
//    }
}
