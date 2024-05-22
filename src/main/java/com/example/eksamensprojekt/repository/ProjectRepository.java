package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {
    @Autowired
    private ConnectionManager connectionManager;

    public void createProject(Project project) {
        String sql = "INSERT INTO project (projectName, projectDescription, projectStartDate, projectEndDate) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, project.getProjectName());
            stmt.setString(2, project.getProjectDescription());

            if (project.getStartDate() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            if (project.getEndDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProject(String name, Project editProject) {
        String editSql = "UPDATE project SET projectName = ?, projectDescription = ?, projectStartDate = ?, projectEndDate = ? WHERE projectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(editSql)
        ) {
            stmt.setString(1, editProject.getProjectName());
            stmt.setString(2, editProject.getProjectDescription());

            if (editProject.getStartDate() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(editProject.getStartDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            if (editProject.getEndDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(editProject.getEndDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setString(5, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(String projectName) {
        String deleteSql = "DELETE FROM project WHERE projectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setString(1, projectName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> findAllProject() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT projectId, projectName, projectDescription, projectStartDate, projectEndDate FROM project";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectDescription(rs.getString("projectDescription"));

                if (rs.getDate("projectStartDate") != null) {
                    project.setStartDate(rs.getDate("projectStartDate").toLocalDate());
                }
                if (rs.getDate("projectEndDate") != null) {
                    project.setEndDate(rs.getDate("projectEndDate").toLocalDate());
                }

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public Project findProjectByName(String name) {
        String sql = "SELECT projectId, projectName, projectDescription, projectStartDate, projectEndDate FROM project WHERE projectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("projectId"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setProjectDescription(rs.getString("projectDescription"));
                    if (rs.getDate("projectStartDate") != null) {
                        project.setStartDate(rs.getDate("projectStartDate").toLocalDate());
                    }
                    if (rs.getDate("projectEndDate") != null) {
                        project.setEndDate(rs.getDate("projectEndDate").toLocalDate());
                    }

                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Project findProjectById(int projectId) {
        String sql = "SELECT projectId, projectName, projectDescription, projectStartDate, projectEndDate FROM project WHERE projectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("projectId"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setProjectDescription(rs.getString("projectDescription"));
                    if (rs.getDate("projectStartDate") != null) {
                        project.setStartDate(rs.getDate("projectStartDate").toLocalDate());
                    }
                    if (rs.getDate("projectEndDate") != null) {
                        project.setEndDate(rs.getDate("projectEndDate").toLocalDate());
                    }

                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
