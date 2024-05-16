package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.eksamensprojekt.repository.ConnectionManager;

import java.sql.*;

@Repository
public class UserRepository {

    @Autowired
    private ConnectionManager connectionManager;

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO user (username, user_password, first_name, last_name, email, role,userId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();
        }
    }

    public boolean verifyUserLogin(String username, String userPassword) throws SQLException {
        String sql = "SELECT username, user_password FROM user WHERE username = ? AND user_password = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returnerer true, hvis der er mindst én række i resultatet
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Håndter fejl i forbindelse med databaseadgangen
            return false;
        }
    }




    public int getUserId(String username, String userPassword) throws SQLException {
        String sql = "SELECT user_id FROM user WHERE username = ? AND user_password = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_id");
                } else {
                    return -1;
                }
            }
        }
    }



    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, password, role, userId) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getUserId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}



