package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.service.ProjectService;
import com.example.eksamensprojekt.service.SubProjectService;
import com.example.eksamensprojekt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;

    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService,SubProjectService subProjectService,TaskService taskService){
     this.projectService = projectService;
     this.subProjectService = subProjectService;
     this.taskService = taskService;

    }
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/create")
    public String CreateProject(Model model){
        Project project = new Project();
        return "create";
    }
}

