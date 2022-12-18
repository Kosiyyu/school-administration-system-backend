package com.proj.restapi.controller;

import com.proj.restapi.model.Rating;
import com.proj.restapi.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("")
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> findAll(){
        List<Rating> ratings = ratingService.findAll();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<Rating> findById(@PathVariable long id){
        Rating rating = null;
        try {
            rating = ratingService.findById(id);
        }
        catch (Exception e){

        }
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PostMapping("/ratings/{subjectId}/{studentId}")
    public ResponseEntity<Rating> save(@RequestBody Rating rating, @PathVariable long subjectId, @PathVariable long studentId){
        try{
            ratingService.save(rating,subjectId, studentId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Rating> delete(@PathVariable long id){
        try{
            ratingService.delete(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
