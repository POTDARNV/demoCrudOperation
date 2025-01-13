package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Department;

import com.example.crudOperations.demoCrudOperation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public Department addDepartment(@RequestBody Department department)
    {
        return departmentService.addDepartment(department);//,department.getEmployee()
    }

    @GetMapping
    public List<Department> departments()
    {
        return departmentService.getAllDep();
    }

    @DeleteMapping
    public String deletedept(@RequestBody int id)
    {
        return departmentService.deleteDept(id);
    }
    @PutMapping
    public Department department(@RequestBody Department department)
    {
        return departmentService.updateDept(department);
    }
}
