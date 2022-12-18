package com.proj.restapi.controller;

import com.proj.restapi.model.Subject;
import com.proj.restapi.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
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
            throw new ResponseStatusException(HttpStatus.OK, "Subject not found");
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
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









}
