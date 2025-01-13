package com.example.crudOperations.demoCrudOperation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String projectName;
    private String projectType;
    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Employee> employee;
}
