package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project) {
        projectRepository.createProject(project);

    }
    public List<Project> findProject() {
        return projectRepository.findAllProject();
    }

    public void editProject(String name, Project editProject) {
        projectRepository.editProject(name, editProject);
    }

    public void deleteProject(String projectName) {
        projectRepository.deleteProject(projectName);
    }

    public Project findProjectByName(String projectName) {
        return projectRepository.findProjectByName(projectName);
    }


}