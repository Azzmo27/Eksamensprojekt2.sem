package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Task;
import com.example.eksamensprojekt.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTaskName("Test Task");
        task.setTaskDescription("Description");
        task.setTaskStartDate(LocalDate.now());
        task.setTaskEndDate(LocalDate.now().plusDays(1));
        task.setTimeEstimate(5);

        taskRepository.createTask(task);

        assertNotNull(task.getTaskId());
    }

    @Test
    public void testEditTask() {

        int taskId = 1;
        Task editTask = new Task();
        editTask.setTaskName("Edited Task");
        editTask.setTaskDescription("Edited Description");
        editTask.setTaskStartDate(LocalDate.now());
        editTask.setTaskEndDate(LocalDate.now().plusDays(2));
        editTask.setTimeEstimate(8);

        taskRepository.editTask(taskId, editTask);

        Task updatedTask = taskRepository.findTaskById(taskId);
        assertEquals(editTask.getTaskName(), updatedTask.getTaskName());
        assertEquals(editTask.getTaskDescription(), updatedTask.getTaskDescription());
        assertEquals(editTask.getTaskStartDate(), updatedTask.getTaskStartDate());
        assertEquals(editTask.getTaskEndDate(), updatedTask.getTaskEndDate());
        assertEquals(editTask.getTimeEstimate(), updatedTask.getTimeEstimate());
    }

    @Test
    public void testDeleteTask() {

        int taskId = 1;
        taskRepository.deleteTask(taskId);
        assertNull(taskRepository.findTaskById(taskId));
    }



    @Test
    public void testFindTaskById() {

        int taskId = 1;
        Task task = taskRepository.findTaskById(taskId);
        assertNotNull(task, () -> "Task with ID " + taskId + " should not be null");
        assertEquals(taskId, task.getTaskId(), "Task ID should match");
    }

    @Test
    public void testFindAllTasks() {

        List<Task> tasks = taskRepository.findAllTasks();

       
        assertNotNull(tasks);

      
        assertFalse(tasks.isEmpty());
    }

    @Test
    public void testFindTasksBySubProjectId() {

        int subProjectId = 1;
        List<Task> tasks = taskRepository.findTasksBySubProjectId(subProjectId);
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
    }
}
