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



    @GetMapping("/lessonUnits")
    public ResponseEntity<List<LessonUnit>> findAll() {
        return new ResponseEntity<>(lessonUnitService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lessonUnits/{id}")
    public ResponseEntity<LessonUnit> findById(@PathVariable long id) {
        return lessonUnitService.findById(id)
                .map(lessonUnit -> new ResponseEntity<>(lessonUnit, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/lessonUnits/{studyGroupId}/{subjectId}")
    public ResponseEntity<LessonUnit> save(@RequestBody LessonUnit lessonUnit, @PathVariable long studyGroupId, @PathVariable long subjectId) {
        LessonUnit savedLessonUnit = lessonUnitService.save(lessonUnit, studyGroupId, subjectId);
        return new ResponseEntity<>(savedLessonUnit, HttpStatus.CREATED);
    }

    @DeleteMapping("/lessonUnits/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        lessonUnitService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
