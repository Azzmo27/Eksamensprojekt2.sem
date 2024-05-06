package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    public void createProject(Project project) {
        projectRepository.createProject(project);

    }

}
