package com.proj.restapi.controller;

import com.proj.restapi.model.Grade;
import com.proj.restapi.model.Student;
import com.proj.restapi.model.Subject;
import com.proj.restapi.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> findAll(){
        List<Grade> grades = gradeService.findAll();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/grades/{id}")
    public ResponseEntity<Grade> findById(@PathVariable long id){
        Grade grade = null;

        try {
            grade = gradeService.findById(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @PostMapping("/grades/{studentId}/{subjectId}")
    public ResponseEntity<Grade> save(@RequestBody Grade grade, @PathVariable long studentId, @PathVariable long subjectId){
        try {
            gradeService.save(grade, subjectId, studentId);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

    @DeleteMapping("/grades/{id}")
    public ResponseEntity<Grade> delete(@PathVariable long id){
        try {
            gradeService.delete(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
