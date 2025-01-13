package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    //#####
    @Query(value = "SELECT e.empid, e.emp_name, e.company_name, e.emp_salary, p.project_id, p.project_name, p.project_type " +
            "FROM Employee e " +
            " LEFT JOIN employee_project ep ON e.empid = ep.employee_id " +
            " LEFT JOIN Project p ON ep.project_id = p.project_id " +
            "WHERE e.empid = :empId", nativeQuery = true)
    public List<Object[]> findEmployeeWithProject(@Param("empId")int empId);
//#######
    @Query(value = "SELECT e.empid,e.emp_name,e.emp_salary,a.address_id,a.address_type,a.city "
    +"FROM Employee e "+
    "RIGHT JOIN address a ON a.address_id = e.address_address_id "+
    "where e.empid=:empId",nativeQuery = true)
    public List<Object[]> findemployeeWithAddress(@Param("empId")int empId);
//######
    @Query(value = "SELECT e.empid,e.emp_name,e.emp_salary,a.address_id,a.address_type,a.city,d.dept_id,d.dept_name "
            +"FROM Employee e "+
            "RIGHT JOIN address a ON a.address_id = e.address_address_id "+
            "LEFT JOIN Department d ON d.dept_id = e.department_dept_id "+
            "where e.empid=:empId",nativeQuery = true)
    public List<Object[]> findemployeeWithAddressandDepartment(@Param("empId")int empId);

}
