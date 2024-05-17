package com.example.eksamensprojekt.repository;


import com.example.eksamensprojekt.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    @Autowired
    private ConnectionManager connectionManager;

    public void createTask(Task task) {
        String sql = "INSERT INTO tasks (taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getTaskDescription());


            if (task.getTaskStartDate() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(task.getTaskStartDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            // Null check for endDate
            if (task.getTaskEndDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(task.getTaskEndDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setInt(5, task.getTimeEstimate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editTask(String name, Task editTask) {
        String editSql = "UPDATE tasks SET taskName = ?, taskDescription = ?, taskStartDate = ?, taskEndDate = ?, timeEstimate = ? WHERE taskName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(editSql)
        ) {
            stmt.setString(1, editTask.getTaskName());
            stmt.setString(2, editTask.getTaskDescription());


            if (editTask.getTaskStartDate() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(editTask.getTaskStartDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }


            if (editTask.getTaskEndDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(editTask.getTaskEndDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setInt(5, editTask.getTimeEstimate());
            stmt.setString(6, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTask(String taskName) {
        String deleteSql = "DELETE FROM Task WHERE taskName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setString(1, taskName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM task";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskName(rs.getString("taskName"));
                task.setTaskDescription(rs.getString("taskDescription"));
                task.setTaskStartDate(rs.getDate("taskStartDate").toLocalDate());
                task.setTaskEndDate(rs.getDate("taskEndDate").toLocalDate());
                task.setTimeEstimate(rs.getInt("timeEstimate"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Task findTaskByName(String taskName) {
        String sql = "SELECT taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM Task WHERE taskName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, taskName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setTaskName(rs.getString("taskName"));
                    task.setTaskDescription(rs.getString("taskDescription"));

                    if (rs.getDate("taskStartDate") != null) {
                        task.setTaskStartDate(rs.getDate("taskStartDate").toLocalDate());
                    }
                    if (rs.getDate("taskEndDate") != null) {
                        task.setTaskEndDate(rs.getDate("taskEndDate").toLocalDate());
                    }

                    task.setTimeEstimate(rs.getInt("timeEstimate"));

                    return task;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> findTasksBySubProjectId(int subProjectId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM task WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, subProjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setTaskName(rs.getString("taskName"));
                    task.setTaskDescription(rs.getString("taskDescription"));
                    task.setTaskStartDate(rs.getDate("taskStartDate").toLocalDate());
                    task.setTaskEndDate(rs.getDate("taskEndDate").toLocalDate());
                    task.setTimeEstimate(rs.getInt("timeEstimate"));

                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

}
