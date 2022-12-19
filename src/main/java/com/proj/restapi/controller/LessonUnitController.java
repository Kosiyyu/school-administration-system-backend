package com.proj.restapi.controller;

import com.proj.restapi.model.LessonUnit;
import com.proj.restapi.model.Rating;
import com.proj.restapi.service.LessonUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("")
public class LessonUnitController {

    private LessonUnitService lessonUnitService;

    public LessonUnitController(LessonUnitService lessonUnitService) {
        this.lessonUnitService = lessonUnitService;
    }

    public ResponseEntity<List<LessonUnit>> findAll(){
        List<LessonUnit> lessonUnits = lessonUnitService.findAll();
        return new ResponseEntity<>(lessonUnits, HttpStatus.OK);
    }

    @GetMapping("/lessonUnits/{id}")
    public ResponseEntity<LessonUnit> findById(@PathVariable long id){
        LessonUnit lessonUnit = null;

        try {
            lessonUnit = lessonUnitService.findById(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return new ResponseEntity<>(lessonUnit, HttpStatus.OK);
    }

    @PostMapping("/lessonUnits/{studyGroupId}/{subjectId}")
    public ResponseEntity<LessonUnit> save(@RequestBody LessonUnit lessonUnit, @PathVariable long studyGroupId, @PathVariable long subjectId){
        try {
            lessonUnitService.save(lessonUnit, studyGroupId, subjectId);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(lessonUnit,HttpStatus.CREATED);//on chyba nie bedzie mial subjecta i studyGroup zwracanego ;\\, czhyba raczej tak xd i tak wszedzie jest
    }

    @DeleteMapping("/lessonUnits/{id}")
    public ResponseEntity<Rating> delete(@PathVariable long id){
        try{
            lessonUnitService.delete(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
