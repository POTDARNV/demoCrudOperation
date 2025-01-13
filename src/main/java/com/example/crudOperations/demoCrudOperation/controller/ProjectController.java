package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Project;
import com.example.crudOperations.demoCrudOperation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping
    public Project project(@RequestBody Project project)
    {
        return projectService.addProject(project);
    }

    @GetMapping
    public List<Project> projects()
{
    return projectService.getprojects();
}
@DeleteMapping
    public String deleteProject(@RequestBody int id)
{
    projectService.deleteProject(id);
    return "Deleted";
}



}
