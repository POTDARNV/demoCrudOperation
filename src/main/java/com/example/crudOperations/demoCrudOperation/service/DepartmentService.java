package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Department;
import com.example.crudOperations.demoCrudOperation.model.Employee;
import com.example.crudOperations.demoCrudOperation.repo.DepartmentRepo;
import com.example.crudOperations.demoCrudOperation.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo  departmentRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    public Department addDepartment(Department department)//, List<Employee>employees)
    {//employeeRepo.saveAll(employees);
      return departmentRepo.save(department);
    }
    public List<Department> getAllDep()
    {
        return departmentRepo.findAll();
    }

    public String deleteDept(int id)
    {
        departmentRepo.deleteById(id);
        return "Department Deleted";
    }
    public Department updateDept(Department department)
    {
        Department oldDept=departmentRepo.findById(department.getDeptId())
                .orElseThrow(()->new RuntimeException("Department Not Found"));
        oldDept.setDeptName(department.getDeptName());
        return departmentRepo.save(oldDept);

    }




}
