package com.example.eksamensprojekt;

import com.example.eksamensprojekt.repository.ConnectionManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConnectionManagerTest {

    @Autowired
    private ConnectionManager connectionManager;

    @Test
    public void testConnection() {
        try (Connection connection = connectionManager.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}