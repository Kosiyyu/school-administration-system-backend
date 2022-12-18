package com.proj.restapi.service;

import com.proj.restapi.model.Subject;
import com.proj.restapi.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll(){
        List<Subject> subjects = subjectRepository.findAll();
        return subjects;
    }

    public Subject findById(long id){
        Subject subject = subjectRepository.findById(id).get();
        return subject;
    }

    public Subject save(Subject subject){
        subjectRepository.save(subject);
        return subject;
    }

    public void delete(Subject subject){
        subjectRepository.delete(subject);
    }

    public void delete(long id){
        Subject subject = subjectRepository.findById(id).get();
        subjectRepository.delete(subject);
    }
}
