package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.ConnectionManager;
import com.example.eksamensprojekt.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
public class UserRepositoryTest {
    private ConnectionManager connectionManager;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() throws SQLException {
        connectionManager = mock(ConnectionManager.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        userRepository = new UserRepository();
        userRepository.connectionManager = connectionManager;

        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testCreateUser() throws SQLException {

        User user = new User();
        user.setUsername("testUser");
        user.setUserPassword("testPassword");
        user.setFirstName("Navn");
        user.setLastName("Efternavn");
        user.setEmail("Iman@example.com");



        userRepository.createUser(user);


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "testPassword");
        verify(preparedStatement).setString(3, "Navn");
        verify(preparedStatement).setString(4, "Efternavn");
        verify(preparedStatement).setString(5, "Iman@example.com");
        verify(preparedStatement).setString(6, "user");
        verify(preparedStatement).executeUpdate();
        verify(connection).close();
    }

    @Test
    public void testVerifyUserLogin() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);


        boolean result = userRepository.verifyUserLogin("testUser", "testPassword");


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "testPassword");
        verify(preparedStatement).executeQuery();
        assertTrue(result);
        verify(connection).close();
    }

    @Test
    public void testGetUserId() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("user_id")).thenReturn(1);


        int userId = userRepository.getUserId("testUser", "testPassword");


        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "testPassword");
        verify(preparedStatement).executeQuery();
        assertEquals(1, userId);
        verify(connection).close();
    }

    @Test
    public void testSave() throws SQLException {

        User user = new User();
        user.setUsername("testUser");
        user.setUserPassword("testPassword");
        user.setFirstName("Navn");
        user.setLastName("Efternavn");
        user.setEmail("Iman@example.com");



        userRepository.save(user);

        verify(connectionManager).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "testPassword");
        verify(preparedStatement).setString(3, "Navn");
        verify(preparedStatement).setString(4, "Efternavn");
        verify(preparedStatement).setString(5, "Iman@example.com");
        verify(preparedStatement).setString(6, "user");
        verify(preparedStatement).executeUpdate();
        verify(connection).close();
    }
}
