package com.proj.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    private Long id;

    public double value;

    public LocalDateTime creationDate;

    @OneToOne
    public Subject subject;

    @OneToOne
    public Student student;
}
