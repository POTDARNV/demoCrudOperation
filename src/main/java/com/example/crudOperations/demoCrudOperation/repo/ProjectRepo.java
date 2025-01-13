package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {


}
