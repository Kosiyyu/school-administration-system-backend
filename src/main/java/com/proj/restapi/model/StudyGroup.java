package com.proj.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "study_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudyGroup_generator")
    private Long id;

    @Column(name = "max_size")
    private Long maxSize;

    public StudyGroup(Long maxSize) {
        this.maxSize = maxSize;
    }
}
