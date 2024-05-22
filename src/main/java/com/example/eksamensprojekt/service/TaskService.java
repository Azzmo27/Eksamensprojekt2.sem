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

    public void editTask(String name, Task editTask){
        taskRepository.editTask(name, editTask);
    }

    public void deleteTask(String taskName) {
        taskRepository.deleteTask(taskName);
    }

    public Task findTaskByName(String taskName) {
        return taskRepository.findTaskByName(taskName);
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAllTasks();
    }

    public List<Task> findTasksBySubProjectId(int subProjectId) {
        return taskRepository.findTasksBySubProjectId(subProjectId);
    }

}
