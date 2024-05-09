package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ProjectRepository {
    @Autowired
    private final JdbcTemplate template;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }
public void createProject(Project project) {
    String sql = "INSERT INTO project (projectName, description, startDate, endDate) VALUES (?, ?, ?, ?)";
    template.update(sql, project.getProjectName(), project.getDescription(), project.getStartDate(), project.getEndDate());
    }

    public List<Project> findAllProject() {
        String sql = "SELECT project_id, projectName, description, startDate, endDate FROM project";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.query(sql, rowMapper);
    }


}

