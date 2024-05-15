package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubProjectRepository {
    @Autowired
    private ConnectionManager connectionManager;

    public void createSubProject(Subproject subProject) {
        String sql = "INSERT INTO subProject (subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, subProject.getSubProjectName());
            stmt.setString(2, subProject.getSubprojectDescription());
            stmt.setDate(3, java.sql.Date.valueOf(subProject.getSubProjectStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(subProject.getSubProjectEndDate()));
            stmt.setString(5, subProject.getSubProjectStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSubProject(String name, Subproject editedSubProject) {
        String editSql = "UPDATE subProject SET subProjectName = ?, subProjectDescription = ?, subProjectStartDate = ?, subProjectEndDate = ?, subProjectStatus = ? WHERE subProjectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(editSql)
        ) {
            stmt.setString(1, editedSubProject.getSubProjectName());
            stmt.setString(2, editedSubProject.getSubprojectDescription());
            stmt.setDate(3, java.sql.Date.valueOf(editedSubProject.getSubProjectStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(editedSubProject.getSubProjectEndDate()));
            stmt.setString(5, editedSubProject.getSubProjectStatus());
            stmt.setString(6, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubProject(String SubprojectName) {
        String deleteSql = "DELETE FROM subProject WHERE subProjectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)
        ) {
            stmt.setString(1, SubprojectName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subproject> findAllSubProject() {
        List<Subproject> subprojects = new ArrayList<>();
        String sql = "SELECT subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus FROM subProject";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Subproject subproject = new Subproject();
                subproject.setSubProjectName(rs.getString("subProjectName"));
                subproject.setSubProjectDescription(rs.getString("subProjectDescription"));
                subproject.setSubProjectStartDate(rs.getDate("subProjectStartDate").toLocalDate());
                subproject.setSubProjectEndDate(rs.getDate("subProjectEndDate").toLocalDate());
                subproject.setSubProjectStatus(rs.getString("subProjectStatus"));
                subprojects.add(subproject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subprojects;
    }

    public Subproject findSubProjectByName(String name) {
        String sql = "SELECT subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus FROM subProject WHERE subProjectName = ?";
        try (
                Connection conn = connectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subproject subproject = new Subproject();
                    subproject.setSubProjectName(rs.getString("subProjectName"));
                    subproject.setSubProjectDescription(rs.getString("subProjectDescription"));
                    subproject.setSubProjectStartDate(rs.getDate("subProjectStartDate").toLocalDate());
                    subproject.setSubProjectEndDate(rs.getDate("subProjectEndDate").toLocalDate());
                    subproject.setSubProjectStatus(rs.getString("subProjectStatus"));
                    return subproject;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}