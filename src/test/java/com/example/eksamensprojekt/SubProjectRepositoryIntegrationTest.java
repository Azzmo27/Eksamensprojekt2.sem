package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import com.example.eksamensprojekt.service.SubProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest

public class SubProjectRepositoryIntegrationTest {

    @Autowired
    private SubProjectService subProjectService;

    @MockBean
    private SubProjectRepository subProjectRepository;

    @Test
    public void testCreateSubProject() {
        // Arrange
        Subproject subproject = new Subproject("TestSubProject", "Test Description", LocalDate.now(), LocalDate.now(), "Active");


        subProjectRepository.createSubProject(subproject);

        Subproject createdSubproject = subProjectRepository.findSubProjectByName("TestSubProject");
        assertNotNull(createdSubproject);
        assertEquals(subproject.getSubProjectName(), createdSubproject.getSubProjectName());
    }

    @Test
    public void testEditSubProject() {
        // Arrange
        Subproject subproject = new Subproject("TestSubProject", "Updated Description", LocalDate.now(), LocalDate.now(), "Active");

        // Act
        subProjectRepository.editSubProject("TestSubProject", subproject);

        // Assert
        Subproject editedSubproject = subProjectRepository.findSubProjectByName("TestSubProject");
        assertNotNull(editedSubproject);
        assertEquals(subproject.getSubprojectDescription(), editedSubproject.getSubprojectDescription());
    }

    @Test
    public void testDeleteSubProject() {
        // Arrange
        subProjectRepository.createSubProject(new Subproject("TestSubProject", "Test Description", LocalDate.now(), LocalDate.now(), "Active"));

        // Act
        subProjectRepository.deleteSubProject("TestSubProject");

        // Assert
        Subproject deletedSubproject = subProjectRepository.findSubProjectByName("TestSubProject");
        assertNull(deletedSubproject);
    }

    @Test
    public void testFindAllSubProject() {
        // Arrange
        subProjectRepository.createSubProject(new Subproject("TestSubProject1", "Test Description", LocalDate.now(), LocalDate.now(), "Active"));
        subProjectRepository.createSubProject(new Subproject("TestSubProject2", "Test Description", LocalDate.now(), LocalDate.now(), "Active"));

        // Act
        List<Subproject> subprojects = subProjectRepository.findAllSubProject();

        // Assert
        assertNotNull(subprojects);
        assertEquals(2, subprojects.size());
    }
}
