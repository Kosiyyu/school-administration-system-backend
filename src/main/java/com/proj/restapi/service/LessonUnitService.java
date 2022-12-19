package com.proj.restapi.service;

import com.proj.restapi.model.LessonUnit;
import com.proj.restapi.model.StudyGroup;
import com.proj.restapi.model.Subject;
import com.proj.restapi.repository.LessonUnitRepository;
import com.proj.restapi.repository.StudyGroupRepository;
import com.proj.restapi.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonUnitService implements ServiceInterface<LessonUnit>{

    private LessonUnitRepository lessonUnitRepository;

    private StudyGroupRepository studyGroupRepository;

    private SubjectRepository subjectRepository;

    public LessonUnitService(LessonUnitRepository lessonUnitRepository, StudyGroupRepository studyGroupRepository, SubjectRepository subjectRepository) {
        this.lessonUnitRepository = lessonUnitRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<LessonUnit> findAll() {
        List<LessonUnit> lessonUnits = lessonUnitRepository.findAll();
        return lessonUnits;
    }

    @Override
    public LessonUnit findById(long id) {
        LessonUnit lessonUnit = lessonUnitRepository.findById(id).get();
        return lessonUnit;
    }

    @Override
    public LessonUnit save(LessonUnit lessonUnit) {
        lessonUnitRepository.save(lessonUnit);
        return lessonUnit;
    }

    public LessonUnit save(LessonUnit lessonUnit, long studyGroupId, long subjectId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        lessonUnit.setStudyGroup(studyGroup);
        lessonUnit.setSubject(subject);
        lessonUnitRepository.save(lessonUnit);
        return lessonUnit;
    }

    @Override
    public void delete(long id) {
        LessonUnit lessonUnit = lessonUnitRepository.findById(id).get();
        lessonUnitRepository.delete(lessonUnit);
    }
}
