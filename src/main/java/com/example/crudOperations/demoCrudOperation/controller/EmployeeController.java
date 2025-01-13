package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Employee;
import com.example.crudOperations.demoCrudOperation.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//logging.level.root=TRACE
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    Logger logger= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService service;
     @PostMapping("/addEmp")
     public Employee addEmployeeWithAddress(@RequestBody Employee employee)
     {
         System.err.println(employee);
         logger.info("in the Add Method of Controller");
         return service.saveemployeeWithAddress(employee,employee.getAddress());
     }
     @GetMapping("getAll")
     public List<Employee>findemployees(){
    logger.info("in the Fetch Method of Controller");
    return service.getAllemployees();
     }
     @DeleteMapping
     public String deleteEmployee(@RequestBody int id)
     {
         logger.info("in the delete method of controller");
         return service.deleteemployee(id);
     }
     @PutMapping
     public Employee updateEmployee(@RequestBody Employee employee)
     {
         logger.info("in the update method of controller");
         return service.updateEmployee(employee);
     }

@GetMapping("/id")
    public List<Object[]> findEmployeeWithProjects(@RequestBody int employeeId) {
    return service.getEmployeeWithProjects(employeeId);
}
@GetMapping("/aid")
    public List<Object[]> getemployeeWithAddress(@RequestBody int id)
    {
        return service.findemployeeWithAddress(id);
    }

    @GetMapping("/did")
    public List<Object[]> findemployeeWithAddressAndDepartment(@RequestBody int id)
    {
        return service.findemployeeWithAddressandDepartment(id);
    }

}
