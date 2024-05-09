package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.service.ProjectService;
import com.example.eksamensprojekt.service.SubProjectService;
import com.example.eksamensprojekt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;

import java.util.List;

@Controller
public class ProjectController implements ErrorController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, SubProjectService subProjectService, TaskService taskService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String CreateProject(Model model) {
        Project project = new Project();
        return "create";
    }

    @PostMapping("/create")
    public String createProject(Project project, RedirectAttributes redirectAttributes) {
        projectService.createProject(project);
        return "redirect:/showProjects";
    }

    @GetMapping("/showProject")
    public String showProjects(Model model) {
        List<Project> projects = projectService.findProject();
        model.addAttribute("projects", projects);
        return "showProject";
    }

    // Handle errors
    @GetMapping("/error")
    public String handleError() {
        return "/error";
    }

    @GetMapping("/error")
    public String getErrorPath() {
        return "/error";
    }


    @GetMapping("/{projectName}/delete")
    public String deleteProject(@PathVariable("projectName") String projectName) {
        projectService.deleteProject(projectName);
        return "redirect:/projects";
    }

    @GetMapping("/{projectName}/updateProject")
    public String updateProject(@PathVariable("projectName") String projectName, Model model) {
        Project project = projectService.findProjectByName(projectName);

        model.addAttribute("updateObject", project);
        // Tilføj andre relevante modelattributter efter behov
        return "updateProject"; // Navnet på din HTML-skabelon til opdatering af projektet
    }


    @PostMapping("/updateProject")
    public String updatedProject(@ModelAttribute Project updatedProject) {
        projectService.updateProject(updatedProject.getProjectName(), updatedProject);
        return "redirect:/projects";
    }

}
