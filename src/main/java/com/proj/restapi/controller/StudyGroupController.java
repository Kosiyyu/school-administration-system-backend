package com.proj.restapi.controller;

import com.proj.restapi.model.StudyGroup;
import com.proj.restapi.service.StudyGroupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/download")
    public ResponseEntity<String> getStudyGroupsAsCsv() {
        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "studyGroups.csv");

        // Create the CSV data as a string
        StringBuilder csvData = new StringBuilder();
        csvData.append("ID,Max Size\n");
        List<StudyGroup> studyGroups = studyGroupService.findAll();
        for (StudyGroup studyGroup : studyGroups) {
            csvData.append(studyGroup.getId()).append(",").append(studyGroup.getMaxSize()).append("\n");
        }

        // Return the CSV data as the response
        return new ResponseEntity<>(csvData.toString(), headers, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<StudyGroup>> getAll(@RequestParam(required = false) String title) {
        List<StudyGroup> studyGroups = studyGroupService.findAll();
        return new ResponseEntity<>(studyGroups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroup> getById(@PathVariable("id") long id) {
        StudyGroup studyGroup = null;
        try {
            studyGroup = studyGroupService.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Study group not found");
        }
        return new ResponseEntity<>(studyGroup, HttpStatus.OK);
    }

    ///...


    @PostMapping("")
    public ResponseEntity<StudyGroup> create(@RequestBody StudyGroup studyGroup) {
        StudyGroup s = studyGroupService.save(studyGroup);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id) {
        try {
            studyGroupService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Study group not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAll() {
        studyGroupService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
