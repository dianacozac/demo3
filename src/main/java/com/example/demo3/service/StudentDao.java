package com.example.demo3.service;

import com.example.demo3.dao.Dao;
import com.example.demo3.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * implementation of DAO interface
 */

@Service
public class StudentDao implements Dao {

    private static final Logger log = LoggerFactory.getLogger(StudentDao.class);

    @Override
    public List<Student> getAll() {

        List<Student> studentList = new ArrayList<>();

        String filePath = "src/main/resources/us-500.csv";

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(" ");

            log.info(" reading data from " + filePath);

            if (scanner.hasNextLine()) {  //checking if there is a line and escape it(supposed to be the header
                scanner.nextLine();
            }
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                Student student = parseStudent(s);
                studentList.add(student);
            }
        } catch (IOException e) {

            log.error(" file not found: " + filePath);
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentsFiltered(String firstNamePrefix, String lastNamePrefix) {

        if (firstNamePrefix == null && lastNamePrefix == null){
            return getAllName();
        } else if (firstNamePrefix != null && lastNamePrefix == null){
            return getFirstName(firstNamePrefix);
        } else if (firstNamePrefix == null && lastNamePrefix != null) {

            return getLastName(lastNamePrefix);
        } else return getLastAndFirstName(firstNamePrefix,lastNamePrefix);

    }

    Student s= new Student();
    public List<Student> getStudentsSorted(String fieldName) {

        if(Objects.equals(fieldName, s.getFirstName())) {
            return getFirstNameSorted();
        } else if (Objects.equals(fieldName, s.getLastName())){
            return getLastNameSorted();
        }

        else return null;

//        List<Student> list = getAll();
//
//        List<Student> sortedList = list.stream()
//                .sorted(Comparator.comparing(Student::getFirstName))
//                .collect(Collectors.toList());
//
//        return sortedList;

    }

    private List<Student> getFirstNameSorted (){
        List<Student> list = getAll();

        List<Student> sortedList = list.stream()
                .sorted(Comparator.comparing(Student::getFirstName))
                .collect(Collectors.toList());

        return sortedList;
    }

    private List<Student> getLastNameSorted(){
        List<Student> list = getAll();
        List<Student> sortedListLastName = list.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .collect(Collectors.toList());

        return sortedListLastName;
    }

    private  List<Student> getAllName() {
        List<Student> list = getAll();

            return list;

    }

    private List<Student> getFirstName(String firstNamePrefix) {

            List<Student> list = getAll();

            List<Student> filteredList = list.stream().filter(p -> p.getFirstName().startsWith(firstNamePrefix))
                    .collect(Collectors.toList());

            return filteredList;

    }

    private List<Student> getLastName( String lastNamePrefix) {

            List<Student> list = getAll();

            List<Student> filteredList = list.stream().filter(p -> p.getLastName().startsWith(lastNamePrefix))
                    .collect(Collectors.toList());
            return filteredList;

    }

    private List<Student> getLastAndFirstName(String firstNamePrefix, String lastNamePrefix ) {

            List<Student> list = getAll();

            List<Student> filteredList = list.stream().filter(p -> p.getFirstName().startsWith(firstNamePrefix)
                            && p.getLastName().startsWith(lastNamePrefix))
                    .collect(Collectors.toList());
            return filteredList;

     }


    /**
     * method to return a student obtained
     * @param s String representation of a student in CSV format
     * @return a Student object obtained from s
     */
    private Student parseStudent(String s) {

        String[] splits = s.split(",");

        if(splits.length >= 12){
            Student student = new Student();
            student.setFirstName(splits[0].replace("\"", ""));
            student.setLastName(splits[1].replace("\"", ""));

            return student;

        } else {

            System.out.println("invalid string to parse ");
        }

       return null;
    }
}
