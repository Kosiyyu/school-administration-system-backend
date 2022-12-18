package com.proj.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    private Long id;

    @OneToOne
    private Student student;

    private int grade;

    private String content;

    @OneToOne
    private Subject subject;





}
