package com.proj.restapi.controller;

import com.proj.restapi.model.Student;
import com.proj.restapi.service.StudentService;
import com.proj.restapi.service.StudyGroupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class StudentController {

    private final StudentService studentService;

    private final StudyGroupService studyGroupService;

    public StudentController(StudentService studentService, StudyGroupService studyGroupService) {
        this.studentService = studentService;
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/students/download")
    public ResponseEntity<String> getStudentsAsCsv() {
        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "students.csv");

        // Create the CSV data as a string
        StringBuilder csvData = new StringBuilder();
        csvData.append("ID,First Name,Last Name,Birthday Date,Address,Email,Telephone Number,Study Group ID\n");
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            csvData.append(student.getId())
                    .append(",")
                    .append(student.getFirstname())
                    .append(",")
                    .append(student.getLastname())
                    .append(",")
                    .append(student.getBirthdayDate())
                    .append(",")
                    .append(student.getAddress())
                    .append(",")
                    .append(student.getEmail())
                    .append(",")
                    .append(student.getTelephoneNumber())
                    .append(",")
                    .append(student.getStudyGroup().getId())
                    .append("\n");
        }

        // Return the CSV data as the response
        return new ResponseEntity<>(csvData.toString(), headers, HttpStatus.OK);
    }


    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/groups/{id}/students")
    public ResponseEntity<List<Student>> getAllStudentsById(@PathVariable(value = "id") Long id) {
        List<Student> students = studentService.findAll().stream().filter(student -> student.getStudyGroup().getId() == id).collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long id) {
        Student student = null;
        try {
            student = studentService.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/groups/{id}/students")
    public ResponseEntity<Student> createStudent(@PathVariable(value = "id") Long id, @RequestBody Student student) {
        Student s = null;
        try {
            System.out.println(s);
            s = studyGroupService.saveInStudyGroup(id, student);
            System.out.println(s);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody Student student) {
        studentService.update(id, student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
