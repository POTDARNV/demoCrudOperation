package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
