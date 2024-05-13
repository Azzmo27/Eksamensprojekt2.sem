package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class SubProjectRepository {
    @Autowired
    private final JdbcTemplate template;

    public SubProjectRepository(JdbcTemplate template) {
        this.template = template;
    }


    public void createSubProject(Subproject subProject) {
        String sql = "INSERT INTO subProject (subProjectName, subProjectDescription, subProjectStartDate, subProjectendDate) VALUES (?, ?, ?, ?)";
        template.update(sql, subProject.getSubProjectName(), subProject.getSubprojectDescription(), subProject.getSubProjectStartDate(), subProject.getSubProjectEndDate(), subProject.getSubProjectStatus());
    }

    public void editSubProject(String name, Subproject editedSubProject) {
        String editSql = "UPDATE subProject SET subProjectName = ?, subProjectDescription = ?, subProjectStartDate = ?, subProjectendDate = ? WHERE subProjectName = ?";
        template.update(editSql, editedSubProject.getSubProjectName(), editedSubProject.getSubprojectDescription(),
                editedSubProject.getSubProjectStartDate(), editedSubProject.getSubProjectEndDate(), name);
    }

    public void deleteSubProject(String SubprojectName) {
        String deleteSql = "DELETE FROM subProject WHERE subProjectName = ?";
        template.update(deleteSql, SubprojectName);
    }

    public List<Subproject> findAllSubProject() {
        String sql = "SELECT subProject_id, subProjectName, subProjectDescription, subProjectStartDate, subProjectendDate FROM subProject";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return template.query(sql, rowMapper);
    }

    public Subproject findSubProjectByName(String name) {
        String sql = "SELECT subProject_id, subProjectName, subProjectdescription, subProjectStartDate, subProjectendDate FROM subProject WHERE subProjectName = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return template.queryForObject(sql, rowMapper, name);
    }


}
