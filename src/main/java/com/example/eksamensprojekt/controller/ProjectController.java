package com.example.eksamensprojekt.controller;
import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import com.example.eksamensprojekt.service.ProjectService;
import com.example.eksamensprojekt.service.SubProjectService;
import com.example.eksamensprojekt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import java.util.List;

@Controller
public class ProjectController implements ErrorController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;
    private final TaskService taskService;

    private final SubProjectRepository subProjectRepository;

    @Autowired
    public ProjectController(ProjectService projectService, SubProjectService subProjectService, TaskService taskService, SubProjectRepository subProjectRepository) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
        this.subProjectRepository = subProjectRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create";
    }

    @PostMapping("/create")
    public String createProject(Project project, RedirectAttributes redirectAttributes) {
        projectService.createProject(project);
        return "redirect:/showProject";
    }

    @GetMapping("/showProject")
    public String showProjects(Model model) {
        List<Project> projects = projectService.findProject();
        model.addAttribute("projects", projects);
        return "showProject";
    }

    @GetMapping("/error")
    public String handleError() {
        return "errorPage";
    }

     @PostMapping("/{projectName}/delete")
    public String deleteProjectByName(@PathVariable("projectName") String projectName) {
        projectService.deleteProject(projectName);
        return "redirect:/showProject";
    }

    @GetMapping("/{projectName}/editProject")
    public String editProject(@PathVariable("projectName") String projectName, Model model) {
        Project project = projectService.findProjectByName(projectName);
        model.addAttribute("editProject", project);
        return "editProject";
    }

    @PostMapping("/editProject")
    public String editProject(@ModelAttribute Project editProject) {
        projectService.editProject(editProject.getProjectName(), editProject);
        return "redirect:/showProject";
    }
    @PostMapping("/createdSubProject")
    public String createSubProject(@ModelAttribute("subProject") Subproject subProject) {
        subProjectService.createSubProject(subProject);
        // Redirecting back to chosenProject page
        return "redirect:/chosenProject/" + subProject.getSubProjectName();
    }

    @PostMapping("/chosenProject/{projectName}/createSubproject")
    public String createSubProject(@PathVariable("projectName") String projectName, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        model.addAttribute("chosenProject", chosenProject);
        return "chosenProject"; // Return to chosenProject page
    }


    @PostMapping("/deleteSubProject/{subProjectName}")
    public String deleteSubProject(@PathVariable String subProjectName) {
        Subproject deletedSubproject = subProjectService.findSubProjectByName(subProjectName);
        String projectName = deletedSubproject.getSubProjectName();
        subProjectService.deleteSubProject(subProjectName);
        return "redirect:/chosenProject/" + projectName;
    }

    @GetMapping("/{subProjectName}/editSubProject")
    public String editSubProject(@PathVariable("subProjectName") String subProjectName, Model model) {
        Subproject project = subProjectService.findSubProjectByName(subProjectName);
        model.addAttribute("editSubProject", project);
        String projectName = project.getSubProjectName();
        return "redirect:/chosenProject/" + projectName;
    }

    @GetMapping("/chosenProject/{projectName}")
    public String showChosenProject(@PathVariable("projectName") String projectName, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        List<Subproject> subprojects = subProjectService.findAllSubProject();
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subprojects", subprojects);
        return "chosenProject";
    }

    @GetMapping("/chosenProject/{projectName}/createSubproject")
    public String createSubProject(@PathVariable("projectName") String projectName, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        model.addAttribute("chosenProject", chosenProject);
        return "chosenProject";
    }

}




