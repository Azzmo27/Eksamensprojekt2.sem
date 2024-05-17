package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.ConnectionManager;
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
        String sql = "INSERT INTO subProject (subProjectId, projectId, subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, projectId);
            stmt.setString(2, subProject.getSubProjectName());
            stmt.setString(3, subProject.getSubProjectDescription());

            if (subProject.getSubProjectStartDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(subProject.getSubProjectStartDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            if (subProject.getSubProjectEndDate() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(subProject.getSubProjectEndDate()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, subProject.getSubProjectStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSubProject(int subProjectId, Subproject editedSubProject) {
        String editSql = "UPDATE subProject SET projectId = ?, subProjectName = ?, subProjectDescription = ?, subProjectStartDate = ?, subProjectEndDate = ?, subProjectStatus = ? WHERE subProjectId = ?";

        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(editSql)
        ) {
            stmt.setInt(1, editedSubProject.getProjectId());
            stmt.setString(2, editedSubProject.getSubProjectName());
            stmt.setString(3, editedSubProject.getSubProjectDescription());
            stmt.setDate(4, java.sql.Date.valueOf(editedSubProject.getSubProjectStartDate()));
            stmt.setDate(5, java.sql.Date.valueOf(editedSubProject.getSubProjectEndDate()));
            stmt.setString(6, editedSubProject.getSubProjectStatus());
            stmt.setInt(7, subProjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubProject(int subProjectId) {
        String deleteSql = "DELETE FROM subProject WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setInt(1, subProjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Subproject> findAllSubProject() {
        String sql = "SELECT * FROM subProject";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProjects;
    }


    public Subproject findSubProjectById(int subProjectId) {
        String sql = "SELECT subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus FROM subProject WHERE subProjectId = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, subProjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subproject subproject = new Subproject();
                    subproject.setSubProjectName(rs.getString("subProjectName"));
                    subproject.setSubProjectDescription(rs.getString("subProjectDescription"));
                    subproject.setSubProjectStartDate(rs.getDate("subProjectStartDate").toLocalDate());
                    subproject.setSubProjectEndDate(rs.getDate("subProjectEndDate").toLocalDate());
                    subproject.setSubProjectStatus(rs.getString("subProjectStatus"));
                    subproject.setSubProjectId(subProjectId); // Set the correct ID for the subproject
                    return subproject;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String findProjectNameBySubProjectId(int subProjectId) {
        // Find subprojektet baseret på subprojektets ID
        Subproject subproject = findSubProjectById(subProjectId);

        // Hvis subprojektet ikke eksisterer, returner null
        if (subproject == null) {
            return null;
        }

        // Hent projektets navn fra databasen baseret på subprojektets ID
        // Dette er kun et fiktivt eksempel og afhænger af din databasestruktur
        String projectName = subproject.getSubProjectName();

        return projectName;
    }

}
