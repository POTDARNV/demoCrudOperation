package com.example.crudOperations.demoCrudOperation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int deptId;
     private String deptName;
     @OneToMany(mappedBy = "department")
     @JsonIgnore
     private List<Employee> employee;
}
