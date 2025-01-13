   package com.example.crudOperations.demoCrudOperation.model;

   import jakarta.persistence.*;
   import lombok.Data;

   import java.util.List;

   @Entity
   @Data
   public  class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int empid;
      private String empName;
      private String companyName;
      private double empSalary;
      @OneToOne(cascade = CascadeType.ALL)
      private Address address;
      @ManyToOne
      private Department department;
      @ManyToMany
      @JoinTable(name = "employee_project",
              joinColumns = @JoinColumn(name="employee_id")
      ,inverseJoinColumns = @JoinColumn(name = "project_id"))
      private List<Project> projects;

   }
