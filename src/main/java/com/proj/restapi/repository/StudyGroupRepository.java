package com.proj.restapi.repository;

import com.proj.restapi.model.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository extends JpaRepository<StudyGroup,Long> {
}
