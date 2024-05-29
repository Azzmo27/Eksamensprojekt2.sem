package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Subproject;
import com.example.eksamensprojekt.repository.SubProjectRepository;
import com.example.eksamensprojekt.service.SubProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.util.List;
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SubProjectRepositoryIntegrationTest {

    @Autowired
    private SubProjectService subProjectService;

    @Autowired
    private SubProjectRepository subProjectRepository;

    @Test
    public void testCreateSubProject() {


        Subproject subproject = new Subproject("TestSubProject", "Test Description", LocalDate.now(), LocalDate.now(), "Active", 2);

        subProjectService.createSubProject(subproject, subproject.getProjectId());


        Subproject createdSubproject = subProjectRepository.findSubProjectById(subproject.getSubProjectId());
        assertNotNull(createdSubproject);
        assertEquals(subproject.getSubProjectName(), createdSubproject.getSubProjectName());
    }

    @Test
    public void testEditSubProject() {

        Subproject subproject = new Subproject("TestSubProject", "Updated Description", LocalDate.now(), LocalDate.now(), "Active", 2);
        subProjectService.createSubProject(subproject, subproject.getProjectId());


        subproject.setSubProjectDescription("Updated Description");
        subProjectService.editSubProject(subproject.getSubProjectId(), subproject);


        Subproject editedSubproject = subProjectRepository.findSubProjectById(subproject.getSubProjectId());
        assertNotNull(editedSubproject);
        assertEquals(subproject.getSubProjectDescription(), editedSubproject.getSubProjectDescription());
    }

    @Test
    public void testDeleteSubProject() {

        Subproject subproject = new Subproject("TestSubProject", "Test Description", LocalDate.now(), LocalDate.now(), "Active", 2);
        subProjectService.createSubProject(subproject, subproject.getProjectId());


        subProjectService.deleteSubProject(subproject.getSubProjectId());


        Subproject deletedSubproject = subProjectRepository.findSubProjectById(subproject.getSubProjectId());
        assertNull(deletedSubproject, "The deletedSubproject should be null after deletion");
    }




}
