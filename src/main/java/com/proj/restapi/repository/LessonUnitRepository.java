package com.proj.restapi.repository;

import com.proj.restapi.model.LessonUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonUnitRepository extends JpaRepository<LessonUnit, Long> {
}
