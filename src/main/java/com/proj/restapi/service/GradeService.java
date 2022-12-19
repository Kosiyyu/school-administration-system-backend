package com.proj.restapi.service;

import com.proj.restapi.model.Grade;
import com.proj.restapi.model.Student;
import com.proj.restapi.model.Subject;
import com.proj.restapi.repository.GradeRepository;
import com.proj.restapi.repository.StudentRepository;
import com.proj.restapi.repository.StudyGroupRepository;
import com.proj.restapi.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService implements ServiceInterface<Grade>{

    private GradeRepository gradeRepository;

    private SubjectRepository subjectRepository;

    private StudentRepository studentRepository;

    public GradeService(GradeRepository gradeRepository, SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Grade> findAll() {
        List<Grade> grades = gradeRepository.findAll();
        return grades;
    }

    @Override
    public Grade findById(long id) {
        Grade grade = gradeRepository.findById(id).get();
        return grade;
    }

    @Override
    public Grade save(Grade grade) {
        gradeRepository.save(grade);
        return grade;
    }

    public Grade save(Grade grade, long subjectId, long studentId) {
        Subject subject = subjectRepository.findById(studentId).get();
        Student student = studentRepository.findById(studentId).get();
        grade.setSubject(subject);
        grade.setStudent(student);
        gradeRepository.save(grade);
        return grade;
    }

    @Override
    public void delete(long id) {
        Grade grade = gradeRepository.findById(id).get();
        gradeRepository.delete(grade);
    }
}
