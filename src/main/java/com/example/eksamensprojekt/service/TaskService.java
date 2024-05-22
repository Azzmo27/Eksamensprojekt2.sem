package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.Task;
import com.example.eksamensprojekt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task) {
        taskRepository.createTask(task);
    }

    public void editTask(int taskId, Task editTask) {
        taskRepository.editTask(taskId, editTask);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteTask(taskId);
    }

    public Task findTaskById(int taskId) {
        return taskRepository.findTaskById(taskId);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAllTasks();
    }

    public List<Task> findTasksBySubProjectId(int subProjectId) {
        return taskRepository.findTasksBySubProjectId(subProjectId);
    }
}
