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
        subProjectRepository.editSubProject(subProjectId, editedSubProject);
    }

    public void deleteSubProject(int subProjectId) {
        subProjectRepository.deleteSubProject(subProjectId);
    }

    public List<Subproject> findAllSubProject() {
        return subProjectRepository.findAllSubProject();
    }

    public Subproject findSubProjectById(int subProjectId) {
        return subProjectRepository.findSubProjectById(subProjectId);
    }

    public String findProjectNameBySubProjectId(int subProjectId) {
        return subProjectRepository.findProjectNameBySubProjectId(subProjectId);
    }
}
