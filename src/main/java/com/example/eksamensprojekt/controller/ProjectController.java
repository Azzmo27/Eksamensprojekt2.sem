package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.model.Task;
import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import com.example.eksamensprojekt.service.ProjectService;
import com.example.eksamensprojekt.service.SubProjectService;
import com.example.eksamensprojekt.service.TaskService;
import com.example.eksamensprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProjectController implements ErrorController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;
    private final TaskService taskService;
    private final UserService userService;
    private final SubProjectRepository subProjectRepository;

    @Autowired
    public ProjectController(ProjectService projectService, SubProjectService subProjectService, TaskService taskService, UserService userService, SubProjectRepository subProjectRepository) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
        this.userService = userService;
        this.subProjectRepository = subProjectRepository ;
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
        List<Project> projects = projectService.findProjects();
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
    @GetMapping("/createSubProject/{projectId}")
    public String showCreateSubProjectForm(@PathVariable int projectId, Model model) {
        Subproject subProject = new Subproject();
        Project chosenProject = projectService.findProjectById(projectId);
        model.addAttribute("subProject", subProject);
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("projectId", projectId);
        return "createSubProject";
    }

    @PostMapping("/createdSubProject")
    public String createSubProject(@ModelAttribute("subProject") Subproject subProject, @RequestParam("projectId") int projectId, Model model) {
        System.out.println("Received projectId: " + projectId);
        System.out.println("Received subProject: " + subProject);

        subProjectService.createSubProject(subProject, projectId);

        return "redirect:/chosenProject/" + projectId;
    }


    @PostMapping("/deleteSubProject/{subProjectId}")
    public String deleteSubProject(@PathVariable int subProjectId) {
        Subproject deletedSubproject = subProjectService.findSubProjectById(subProjectId);
        if (deletedSubproject != null) {
            int projectId = deletedSubproject.getProjectId();
            subProjectService.deleteSubProject(subProjectId);
            return "redirect:/chosenProject/" + projectId;
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editSubProject/{subProjectId}")
    public String editSubProject(@PathVariable int subProjectId, Model model) {
        Subproject subProject = subProjectService.findSubProjectById(subProjectId);
        String projectName = subProjectService.findProjectNameBySubProjectId(subProjectId);
        Project chosenProject = projectService.findProjectByName(projectName);
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("editSubProject", subProject);
        model.addAttribute("projectName", projectName);
        return "editSubProject";
    }

    @PostMapping("/editSubProject/{subProjectId}")
    public String updateSubProject(@PathVariable int subProjectId, @ModelAttribute Subproject subProject, Model model) {
        subProjectService.editSubProject(subProjectId, subProject);
        return "redirect:/chosenProject/" + subProject.getProjectId();
    }

    @GetMapping("/chosenProject/{projectId}")
    public String showChosenProject(@PathVariable int projectId, Model model) {
        Project chosenProject = projectService.findProjectById(projectId);
        if (chosenProject == null) {
            return "errorPage";
        }
        List<Subproject> subprojects = subProjectService.findSubProjectsByProjectId(projectId);
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subprojects", subprojects);
        model.addAttribute("subProjectId", projectId);
        List<Task> tasks = taskService.findTasksBySubProjectId(projectId);
        model.addAttribute("tasks", tasks);

        return "chosenProject";
    }


    @GetMapping("/chosenProject/{projectId}/subproject/details/{subProjectId}")
    public String showSubProjectDetails(@PathVariable("projectId") int projectId, @PathVariable("subProjectId") int subProjectId, Model model) {
        Project chosenProject = projectService.findProjectById(projectId);
        Subproject subProject = subProjectService.findSubProjectById(subProjectId);
        if (chosenProject == null || subProject == null) {
            return "errorPage";
        }
        List<Task> tasks = taskService.findTasksBySubProjectId(subProjectId);
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subProject", subProject);
        model.addAttribute("tasks", tasks);
        return "subProjectDetails";
    }
    @GetMapping("/showTasks/{subProjectId}")
    public String showTasksBySubProjectId(@PathVariable("subProjectId") int subProjectId, Model model) {
        List<Task> tasks = taskService.findTasksBySubProjectId(subProjectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("subProjectId", subProjectId);
        return "showTask";
    }


    @GetMapping("/createTask")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "showTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/showTasks";
    }
    @GetMapping("/showTasks")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "showTask";
    }

    @PostMapping("/tasks/{taskId}/delete")
    public String deleteTaskById(@PathVariable("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/showTasks";
    }

    @GetMapping("/tasks/{taskId}/editTask")
    public String editTask(@PathVariable("taskId") int taskId, Model model) {
        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task) {
        taskService.editTask(task.getTaskId(), task);
        return "redirect:/showTasks";
    }
}