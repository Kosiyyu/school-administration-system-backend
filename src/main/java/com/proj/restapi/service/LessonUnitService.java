package com.proj.restapi.service;

import com.proj.restapi.model.LessonUnit;
import com.proj.restapi.model.StudyGroup;
import com.proj.restapi.model.Subject;
import com.proj.restapi.repository.LessonUnitRepository;
import com.proj.restapi.repository.StudyGroupRepository;
import com.proj.restapi.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonUnitService {

    private LessonUnitRepository lessonUnitRepository;

    private StudyGroupRepository studyGroupRepository;

    private SubjectRepository subjectRepository;

    public LessonUnitService(LessonUnitRepository lessonUnitRepository, StudyGroupRepository studyGroupRepository, SubjectRepository subjectRepository) {
        this.lessonUnitRepository = lessonUnitRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<LessonUnit> findAll() {
        return lessonUnitRepository.findAll();
    }

    public Optional<LessonUnit> findById(long id) {
        return lessonUnitRepository.findById(id);
    }

    public LessonUnit save(LessonUnit lessonUnit, long studyGroupId, long subjectId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        lessonUnit.setStudyGroup(studyGroup);
        lessonUnit.setSubject(subject);
        lessonUnitRepository.save(lessonUnit);
        return lessonUnit;
    }

    public void deleteById(long id) {
        lessonUnitRepository.deleteById(id);
    }
}