package com.proj.restapi.service;

import com.proj.restapi.model.Student;
import com.proj.restapi.model.StudyGroup;
import com.proj.restapi.repository.StudentRepository;
import com.proj.restapi.repository.StudyGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final StudentRepository studentRepository;

    public StudyGroupService(StudyGroupRepository studyGroupRepository, StudentRepository studentRepository) {
        this.studyGroupRepository = studyGroupRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudyGroup> findAll() {
        return studyGroupRepository.findAll();
    }

    public StudyGroup findById(long id) {
        return studyGroupRepository.findById(id).get();
    }

    public Student saveInStudyGroup(long id, Student student) {
        StudyGroup studyGroup = studyGroupRepository.findById(id).get();
        student.setStudyGroup(studyGroup);
        studentRepository.save(student);
        return student;
    }

    public StudyGroup save(StudyGroup studyGroup) {
        studyGroupRepository.save(studyGroup);
        return studyGroup;
    }

    public void update(long id, StudyGroup studyGroup) {
        StudyGroup s = studyGroupRepository.findById(id).get();
        studyGroupRepository.save(s);
    }

    public void delete(long id) {
        studyGroupRepository.deleteById(id);
    }

    public void deleteAll() {
        studyGroupRepository.deleteAll();
    }
}
