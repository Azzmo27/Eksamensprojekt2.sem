package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProjectTest() {
        Project project = new Project();
        project.setProjectName("Test name");
        project.setDescription("Test description");
        project.setStartDate(LocalDate.of(2024, 12, 23));
        project.setEndDate(LocalDate.of(2025, 1, 23));

        assertEquals("Test name", project.getProjectName());
        assertEquals("Test description", project.getDescription());
        assertEquals(Date.of(2024, 12, 23), project.getStartDate());
        assertEquals(Date.of(2025, 1, 23), project.getEndDate());
    }
}



