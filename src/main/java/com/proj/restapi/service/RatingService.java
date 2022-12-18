package com.proj.restapi.service;

import com.proj.restapi.model.Rating;
import com.proj.restapi.model.Student;
import com.proj.restapi.model.Subject;
import com.proj.restapi.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements ServiceInterface<Rating> {

    private RatingRepository ratingRepository;
    private SubjectService subjectRepository;
    private StudentService studentRepository;

    public RatingService(RatingRepository ratingRepository, SubjectService subjectRepository, StudentService studentRepository) {
        this.ratingRepository = ratingRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Rating> findAll() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    @Override
    public Rating findById(long id) {
        Rating rating = ratingRepository.findById(id).get();
        return rating;
    }

    @Override
    public Rating save(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }

    public Rating save(Rating rating,long subjectId, long studentId) {
        Subject subject = subjectRepository.findById(subjectId);
        Student student = studentRepository.findById(studentId);
        rating.setSubject(subject);
        rating.setStudent(student);
        ratingRepository.save(rating);
        return rating;
    }

    @Override
    public void delete(long id){
        ratingRepository.deleteById(id);
    }

}
