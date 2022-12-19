package com.proj.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lesson_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonUnit {

    @Id
    private Long id;

    private LocalDateTime lessonStart;

    private LocalDateTime lessonEnd;

    @OneToOne
    private StudyGroup studyGroup;

    @OneToOne
    private Subject subject;
}
