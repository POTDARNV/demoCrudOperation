package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Address;
import com.example.crudOperations.demoCrudOperation.model.Employee;
import com.example.crudOperations.demoCrudOperation.repo.AddressRepo;
import com.example.crudOperations.demoCrudOperation.repo.DepartmentRepo;
import com.example.crudOperations.demoCrudOperation.repo.EmployeeRepo;
import com.example.crudOperations.demoCrudOperation.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;
    @Autowired
     private AddressRepo addressRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ProjectRepo repo1;

    public Employee saveemployeeWithAddress(Employee employee , Address address)
    {
        addressRepo.save(address);
            employee.setAddress(address);
        return repo.save(employee);
    }

    public List<Employee>getAllemployees()
    {
        return repo.findAll();
    }
    public String deleteemployee(int id)
    {  repo.deleteById(id);
        return "Employee Deleted";
    }
 public Employee updateEmployee(Employee employee)
 {
     Employee previousEmployee=repo.findById(employee.getEmpid())
             .orElseThrow(()->new RuntimeException("Employee Not Found"));
     previousEmployee.setEmpName(employee.getEmpName());
     previousEmployee.setCompanyName(employee.getCompanyName());
     return repo.save(previousEmployee);
 }
    public List<Object[]> getEmployeeWithProjects(int employeeId) {
        return repo.findEmployeeWithProject(employeeId);
    }

    public List<Object[]> findemployeeWithAddress(int id)
    {
      return   repo.findemployeeWithAddress(id);
    }

    public List<Object[]> findemployeeWithAddressandDepartment(int id)
    {
        return repo.findemployeeWithAddressandDepartment(id);
    }

}
