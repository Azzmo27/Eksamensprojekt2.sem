package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubProjectRepository {
    @Autowired
    private ConnectionManager connectionManager;

    public void createSubProject(Subproject subProject, int projectId) {
        // Update the SQL statement to include projectId correctly
        String sql = "INSERT INTO Subproject (projectId, subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Set the correct parameters
            stmt.setInt(1, projectId);
            stmt.setString(2, subProject.getSubProjectName());
            stmt.setString(3, subProject.getSubProjectDescription());
            stmt.setObject(4, subProject.getSubProjectStartDate(), Types.DATE);
            stmt.setObject(5, subProject.getSubProjectEndDate(), Types.DATE);
            stmt.setString(6, subProject.getSubProjectStatus());


            stmt.executeUpdate();
            System.out.println("Subproject created: " + subProject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSubProject(int subProjectId, Subproject editedSubProject) {
        String editSql = "UPDATE Subproject SET projectId = ?, subProjectName = ?, subProjectDescription = ?, subProjectStartDate = ?, subProjectEndDate = ?, subProjectStatus = ? WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(editSql)
        ) {
            stmt.setInt(1, editedSubProject.getProjectId());
            stmt.setString(2, editedSubProject.getSubProjectName());
            stmt.setString(3, editedSubProject.getSubProjectDescription());
            stmt.setObject(4, editedSubProject.getSubProjectStartDate(), Types.DATE);
            stmt.setObject(5, editedSubProject.getSubProjectEndDate(), Types.DATE);
            stmt.setString(6, editedSubProject.getSubProjectStatus());
            stmt.setInt(7, subProjectId);
            stmt.executeUpdate();
            System.out.println("Subproject edited: " + editedSubProject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubProject(int subProjectId) {
        String deleteSql = "DELETE FROM Subproject WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setInt(1, subProjectId);
            stmt.executeUpdate();
            System.out.println("Subproject deleted with ID: " + subProjectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subproject> findAllSubProject() {
        String sql = "SELECT * FROM Subproject";
        List<Subproject> subProjects = new ArrayList<>();
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Subproject subProject = new Subproject();
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setSubProjectDescription(rs.getString("subProjectDescription"));

                Date startDate = rs.getDate("subProjectStartDate");
                if (startDate != null) {
                    subProject.setSubProjectStartDate(startDate.toLocalDate());
                }
                Date endDate = rs.getDate("subProjectEndDate");
                if (endDate != null) {
                    subProject.setSubProjectEndDate(endDate.toLocalDate());
                }

                subProject.setSubProjectStatus(rs.getString("subProjectStatus"));

                subProjects.add(subProject);
            }
            System.out.println("Found all subprojects: " + subProjects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProjects;
    }

    public Subproject findSubProjectById(int subProjectId) {
        System.out.println("Finding Subproject with ID: " + subProjectId);
        String sql = "SELECT projectId, subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus FROM Subproject WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, subProjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subproject subproject = new Subproject();
                    subproject.setProjectId(rs.getInt("projectId")); // Include projectId
                    subproject.setSubProjectName(rs.getString("subProjectName"));
                    subproject.setSubProjectDescription(rs.getString("subProjectDescription"));
                    Date startDate = rs.getDate("subProjectStartDate");
                    Date endDate = rs.getDate("subProjectEndDate");
                    if (startDate != null) {
                        subproject.setSubProjectStartDate(startDate.toLocalDate());
                    }
                    if (endDate != null) {
                        subproject.setSubProjectEndDate(endDate.toLocalDate());
                    }
                    subproject.setSubProjectStatus(rs.getString("subProjectStatus"));
                    subproject.setSubProjectId(subProjectId); // Set the correct ID for the subproject
                    System.out.println("Found Subproject: " + subProjectId);
                    return subproject;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Subproject not found with ID: " + subProjectId);
        return null;
    }


    public String findProjectNameBySubProjectId(int subProjectId) {
        System.out.println("Finding Project Name for Subproject ID: " + subProjectId);
        Subproject subproject = findSubProjectById(subProjectId);
        if (subproject == null) {
            System.out.println("Subproject not found with ID: " + subProjectId);
            return null;
        }
        String projectName = subproject.getSubProjectName();
        System.out.println("Found Project Name: " + projectName);
        return projectName;
    }

    public List<Subproject> findByProjectId(int projectId) {
        String sql = "SELECT sp.*, sp.subProjectStatus " +
                "FROM Subproject sp " +
                "JOIN Project p ON sp.projectId = p.projectId " +
                "WHERE sp.projectId = ?";


        List<Subproject> subProjects = new ArrayList<>();
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subproject subProject = new Subproject();
                    subProject.setProjectId(rs.getInt("projectId"));
                    subProject.setSubProjectId(rs.getInt("subProjectId"));
                    subProject.setSubProjectName(rs.getString("subProjectName"));
                    subProject.setSubProjectDescription(rs.getString("subProjectDescription"));

                    Date startDate = rs.getDate("subProjectStartDate");
                    if (startDate != null) {
                        subProject.setSubProjectStartDate(startDate.toLocalDate());
                    }

                    Date endDate = rs.getDate("subProjectEndDate");
                    if (endDate != null) {
                        subProject.setSubProjectEndDate(endDate.toLocalDate());
                    }

                    String projectStatus = rs.getString("subProjectStatus");
                    subProject.setSubProjectStatus(projectStatus);

                    subProjects.add(subProject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProjects;
    }
}
