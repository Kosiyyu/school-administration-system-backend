package com.proj.restapi.controller;

import com.proj.restapi.model.Subject;
import com.proj.restapi.service.SubjectService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/download")
    public ResponseEntity<String> downloadSubjectsCsv() throws IOException {
        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "subjects.csv");

        // Create the CSV data as a string
        StringBuilder csvData = new StringBuilder();
        csvData.append("ID,Name\n");
        List<Subject> subjects = subjectService.findAll();
        for (Subject subject : subjects) {
            csvData.append(subject.getId()).append(",").append(subject.getName()).append("\n");
        }

        // Return the CSV data as the response
        return new ResponseEntity<>(csvData.toString(), headers, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<Subject>> getAll(){
        List<Subject> subjects = subjectService.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable long id){
        Subject subject = null;
        try {
            subject = subjectService.findById(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Subject> create(@RequestBody Subject s){
        Subject subject = subjectService.save(s);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        try {
            subjectService.delete(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









}
