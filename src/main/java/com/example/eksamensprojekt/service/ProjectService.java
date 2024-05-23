package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }

    public List<Project> findProjects() {
        return projectRepository.findAllProject();
    }

    public void editProject(int projectId, Project editedProject) {
        System.out.println("Updating Project: " + editedProject);
        projectRepository.editProject(projectId, editedProject);
    }

    public Project findProjectByName(String projectName) {
        return projectRepository.findProjectByName(projectName);
    }

    public Project findProjectById(int projectId) {
        return projectRepository.findProjectById(projectId);
    }

    public void deleteProjectById(int projectId) {
        projectRepository.deleteProjectById(projectId);
    }
}
