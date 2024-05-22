package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;

    @Autowired
    public SubProjectService(SubProjectRepository subProjectRepository) {
        this.subProjectRepository = subProjectRepository;
    }

    public void createSubProject(Subproject subProject, int projectId) {
        subProjectRepository.createSubProject(subProject, projectId);
    }

    public void editSubProject(int subProjectId, Subproject editedSubProject) {
        System.out.println("Editing Subproject with ID: " + subProjectId);
        subProjectRepository.editSubProject(subProjectId, editedSubProject);
        System.out.println("Subproject updated: " + editedSubProject);
    }

    public void deleteSubProject(int subProjectId) {
        subProjectRepository.deleteSubProject(subProjectId);
    }

    public List<Subproject> findAllSubProject() {
        return subProjectRepository.findAllSubProject();
    }

    public Subproject findSubProjectById(int subProjectId) {
        System.out.println("Finding Subproject with ID: " + subProjectId);
        Subproject subProject = subProjectRepository.findSubProjectById(subProjectId);
        System.out.println("Found Subproject: " + subProject);
        return subProject;
    }

    public String findProjectNameBySubProjectId(int subProjectId) {
        System.out.println("Finding Project Name for Subproject ID: " + subProjectId);
        String projectName = subProjectRepository.findProjectNameBySubProjectId(subProjectId);
        System.out.println("Found Project Name: " + projectName);
        return projectName;
    }
    public List<Subproject> findSubProjectsByProjectId(int projectId) {
        return subProjectRepository.findByProjectId(projectId);
    }
}
