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

    @GetMapping("/createSubProject")
    public String showCreateSubProjectForm(Model model) {
        model.addAttribute("subProject", new Subproject());
        return "createSubProject";
    }

    @PostMapping("/createdSubProject/{projectId}") // Tilføjet projectId i stien
    public String createSubProject(@ModelAttribute("subProject") Subproject subProject, @PathVariable int projectId) { // Tilføjet projectId som argument
        subProjectService.createSubProject(subProject, projectId); // Kald createSubProject med både subProject og projectId
        return "redirect:/chosenProject/" + projectId; // Redirect til valgte projekt baseret på projectId
    }


    @PostMapping("/deleteSubProject/{subProjectId}")
    public String deleteSubProject(@PathVariable int subProjectId) {
        Subproject deletedSubproject = subProjectService.findSubProjectById(subProjectId);
        if (deletedSubproject != null) {
            int projectId = deletedSubproject.getProjectId(); // Brug getProjectId() i stedet for getSubProjectId()
            subProjectService.deleteSubProject(subProjectId);
            return "redirect:/chosenProject/" + projectId; // Redirect til valgte projekt baseret på projektets ID
        } else {
            return "redirect:/errorPage"; // Håndter tilfældet hvor deletedSubproject er null
        }

    }

        @GetMapping("/editSubProject/{subProjectId}")
    public String editSubProject(@PathVariable int subProjectId, Model model) {
        Subproject subProject = subProjectService.findSubProjectById(subProjectId);
        String projectName = subProjectService.findProjectNameBySubProjectId(subProjectId);
        model.addAttribute("editSubProject", subProject);
        model.addAttribute("projectName", projectName);
        return "editSubProject";
    }

    @PostMapping("/editSubProject/{subProjectId}")
    public String updateSubProject(@PathVariable int subProjectId, @ModelAttribute Subproject subProject) {
        subProjectService.editSubProject(subProjectId, subProject);
        return "redirect:/chosenProject/" + subProjectId; // Redirect using subProjectId
    }
    @GetMapping("/chosenProject/{projectName}")
    public String showChosenProject(@PathVariable("projectName") String projectName, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        if (chosenProject == null) {
            // Håndter tilfældet hvor projektet ikke blev fundet
            return "errorPage";
        }
        List<Subproject> subprojects = subProjectService.findAllSubProject();
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subprojects", subprojects);
        return "chosenProject";
    }

    @GetMapping("/chosenProject/{projectName}/subproject/details/{subProjectId}")
    public String showSubProjectDetails(@PathVariable("projectName") String projectName, @PathVariable("subProjectId") int subProjectId, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        Subproject subProject = subProjectService.findSubProjectById(subProjectId);
        if (chosenProject == null || subProject == null) {
            // Håndter tilfældet hvor projektet eller underprojektet ikke blev fundet
            return "errorPage";
        }
        List<Task> tasks = taskService.findTasksBySubProjectId(subProjectId);
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subProject", subProject);
        model.addAttribute("tasks", tasks);
        return "chosenProject";
    }


    @GetMapping("/createTask")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "showTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/showTask";
    }

    @GetMapping("/showTasks")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "showTask";
    }

    @PostMapping("/tasks/{taskName}/delete")
    public String deleteTaskByName(@PathVariable("taskName") String taskName) {
        taskService.deleteTask(taskName);
        return "redirect:/showTask";
    }

    @GetMapping("/tasks/{taskName}/editTask")
    public String editTask(@PathVariable("taskName") String taskName, Model model) {
        Task task = taskService.findTaskByName(taskName);
        model.addAttribute("editTask", task);
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task editTask) {
        taskService.editTask(editTask.getTaskName(), editTask);
        return "redirect:/showTask"; // Corrected redirection
    }

    @GetMapping("/login")
    public String frontPage() {
        return "login";
    }

    @PostMapping("/newRegistration")
    public String newRegistration(@ModelAttribute User user, @RequestParam("username") String username, @RequestParam("user_password") String user_password) throws SQLException {
        userService.createNewUser(user);
        int userId = userService.getUserId(username, user_password);
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("user_password") String user_password, Model model) throws SQLException {
        model.addAttribute("user", userService.verifyUserLogin(username, user_password));

        if (userService.verifyUserLogin(username, user_password)) {
            int userId = userService.getUserId(username, user_password);
            return "redirect:/index";
        } else {
            return "login";
        }
    }
}