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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import java.util.List;
import java.sql.SQLException;

@Controller
public class ProjectController implements ErrorController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;
    private final TaskService taskService;

    private final UserService userService;

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
    @GetMapping("/createSubProject")
    public String showCreateSubProjectForm(Model model) {
        model.addAttribute("subProject", new Subproject());
        return "createSubProject";
    }

    @PostMapping("/createdSubProject")
    public String createSubProject(@ModelAttribute("subProject") Subproject subProject) {
        System.out.println(subProject);
        subProjectService.createSubProject(subProject);
        return "redirect:/chosenProject/" + subProject;
    }

    @PostMapping("/deleteSubProject/{subProjectName}")
    public String deleteSubProject(@PathVariable String subProjectName) {
        Subproject deletedSubproject = subProjectService.findSubProjectByName(subProjectName);
        String projectName = deletedSubproject.getSubProjectName();
        subProjectService.deleteSubProject(subProjectName);
        return "redirect:/chosenProject/" + projectName;
    }

    @GetMapping("/editSubProject/{subProjectName}")
    public String editSubProject(@PathVariable String subProjectName, @RequestParam("projectName") String projectName, Model model) {
        Subproject project = subProjectService.findSubProjectByName(subProjectName);
        model.addAttribute("editSubProject", project);
        System.out.println("found subproject" + model);
        return "editSubProject";
    }

    @GetMapping("/chosenProject/{projectName}")
    public String showChosenProject(@PathVariable("projectName") String projectName, Model model) {
        Project chosenProject = projectService.findProjectByName(projectName);
        List<Subproject> subprojects = subProjectService.findAllSubProject();
        model.addAttribute("chosenProject", chosenProject);
        model.addAttribute("subprojects", subprojects);
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
        return "redirect:/tasks/createTask";
    }
    @GetMapping("/showTasks")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "showTasks";
    }

    @PostMapping("/tasks/{taskName}/delete")
    public String deleteTaskByName(@PathVariable("taskName") String taskName) {
        taskService.deleteTask(taskName);
        return "redirect:/showTasks";
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
        return "redirect:/showTask";

    }
    @GetMapping("/")
    public String frontPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationSection() {
        return "registration";
    }

    @PostMapping("/newRegistration")
    public String newRegistration(@ModelAttribute User user, @RequestParam("username") String username,
                                  @RequestParam("user_password") String user_password) throws SQLException {
        userService.createNewUser(user);
        int userId = userService.getUserId(username, user_password);
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("user_password")
    String user_password, Model model) throws SQLException {
        model.addAttribute("user", userService.verifyUserLogin(username, user_password));

        if (userService.verifyUserLogin(username, user_password)) {
            int userId = userService.getUserId(username, user_password);
            return "redirect:/index";
        } else {
            return "login";
        }
    }


}




