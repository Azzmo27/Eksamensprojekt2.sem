package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ConnectionManager;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProjectRepositoryTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testCreateProject() throws SQLException {

        Project project = new Project();
        project.setProjectName("Test Project");
        project.setProjectDescription("Test Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(7));


        projectRepository.createProject(project);

        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testEditProject() throws SQLException {

        int projectId = 1;
        Project editProject = new Project();
        editProject.setProjectName("Updated Project");
        editProject.setProjectDescription("Updated Description");
        editProject.setStartDate(LocalDate.now());
        editProject.setEndDate(LocalDate.now().plusDays(14));


        projectRepository.editProject(projectId, editProject);


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteProjectById() throws SQLException {

        int projectId = 1;


        projectRepository.deleteProjectById(projectId);


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testFindAllProject() throws SQLException {

        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("projectId")).thenReturn(1);
        when(resultSet.getString("projectName")).thenReturn("Test Project");
        when(resultSet.getString("projectDescription")).thenReturn("Test Description");
        when(resultSet.getDate("projectStartDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSet.getDate("projectEndDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now().plusDays(7)));


        List<Project> projects = projectRepository.findAllProject();


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        assertEquals(1, projects.size());
        Project project = projects.get(0);
        assertEquals(1, project.getProjectId());
        assertEquals("Test Project", project.getProjectName());
        assertEquals("Test Description", project.getProjectDescription());
        assertEquals(LocalDate.now(), project.getStartDate());
        assertEquals(LocalDate.now().plusDays(7), project.getEndDate());
    }

    @Test
    public void testFindProjectByName() throws SQLException {

        String projectName = "Test Project";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("projectId")).thenReturn(1);
        when(resultSet.getString("projectName")).thenReturn(projectName);
        when(resultSet.getString("projectDescription")).thenReturn("Test Description");
        when(resultSet.getDate("projectStartDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSet.getDate("projectEndDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now().plusDays(7)));


        Project project = projectRepository.findProjectByName(projectName);


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        assertEquals(1, project.getProjectId());
        assertEquals("Test Project", project.getProjectName());
        assertEquals("Test Description", project.getProjectDescription());
        assertEquals(LocalDate.now(), project.getStartDate());
        assertEquals(LocalDate.now().plusDays(7), project.getEndDate());
    }

    @Test
    public void testFindProjectById() throws SQLException {

        int projectId = 1;
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt
                ("projectId")).thenReturn(projectId);
        when(resultSet.getString("projectName")).thenReturn("Test Project");
        when(resultSet.getString("projectDescription")).thenReturn("Test Description");
        when(resultSet.getDate("projectStartDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSet.getDate("projectEndDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

        Project project = projectRepository.findProjectById(projectId);

        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        assertEquals(1, project.getProjectId());
        assertEquals("Test Project", project.getProjectName());
        assertEquals("Test Description", project.getProjectDescription());
        assertEquals(LocalDate.now(), project.getStartDate());
        assertEquals(LocalDate.now().plusDays(7), project.getEndDate());
    }
}
