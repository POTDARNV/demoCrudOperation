package com.example.crudOperations.demoCrudOperation.service;


import com.example.crudOperations.demoCrudOperation.model.Project;
import com.example.crudOperations.demoCrudOperation.repo.EmployeeRepo;
import com.example.crudOperations.demoCrudOperation.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ProjectService {
    @Autowired
    private ProjectRepo repo;
    @Autowired
    private EmployeeRepo employeeRepo;


    public Project addProject(Project project) {
       return repo.save(project);
    }
   // @Scheduled(fixedRate = 5000)
    public List<Project> getprojects()
    {
        return repo.findAll();
    }
    public String deleteProject(int id)
    {
        repo.deleteById(id);
        return "Project Deleted";
    }


}
