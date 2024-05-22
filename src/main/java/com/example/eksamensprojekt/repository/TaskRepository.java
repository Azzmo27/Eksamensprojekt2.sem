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
        String sql = "INSERT INTO Task (taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate) VALUES (?, ?, ?, ?, ?)";
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

    public void editTask(int taskId, Task editTask) {
        String editSql = "UPDATE Task SET taskName = ?, taskDescription = ?, taskStartDate = ?, taskEndDate = ?, timeEstimate = ? WHERE taskId = ?";
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
            stmt.setInt(6, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int taskId) {
        String deleteSql = "DELETE FROM Task WHERE taskId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task findTaskById(int taskId) {
        String sql = "SELECT taskId, taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM Task WHERE taskId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, taskId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("taskId"));
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

    public List<Task> findAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT taskId, taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM Task";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTaskName(rs.getString("taskName"));
                task.setTaskDescription(rs.getString("taskDescription"));

                Date startDate = rs.getDate("taskStartDate");
                if (startDate != null) {
                    task.setTaskStartDate(startDate.toLocalDate());
                }
                Date endDate = rs.getDate("taskEndDate");
                if (endDate != null) {
                    task.setTaskEndDate(endDate.toLocalDate());
                }

                task.setTimeEstimate(rs.getInt("timeEstimate"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> findTasksBySubProjectId(int subProjectId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT taskId, taskName, taskDescription, taskStartDate, taskEndDate, timeEstimate FROM Task WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, subProjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("taskId"));
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