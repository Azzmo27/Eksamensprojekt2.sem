package com.example.eksamensprojekt.service;


import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.ProjectRepository;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {

    @Autowired
    private final SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository) {
        this.subProjectRepository = subProjectRepository;
    }

    public void createSubProject(Subproject subProject) {
        subProjectRepository.createSubProject(subProject);

    }

    public void editSubProject(String name, Subproject editedSubProject) {
        subProjectRepository.editSubProject(name, editedSubProject);
    }

    public void deleteSubProject(String subProjectName) {
        subProjectRepository.deleteSubProject(subProjectName);
    }

    public List<Subproject> findAllSubProject() {
        return subProjectRepository.findAllSubProject();

    }

    public Subproject findSubProjectByName(String subProjectName) {
        return subProjectRepository.findSubProjectByName(subProjectName);
    }

}
