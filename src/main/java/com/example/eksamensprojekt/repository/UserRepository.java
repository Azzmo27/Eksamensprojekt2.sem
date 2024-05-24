package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    private ConnectionManager connectionManager;

    public void createUser(User user) {
        String sql = "INSERT INTO AppUser (username, user_password, firstName, lastName, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUserLogin(String username, String userPassword) {
        String sql = "SELECT username, user_password FROM AppUser WHERE username = ? AND user_password = ?";
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId(String username, String userPassword) {
        String sql = "SELECT user_id FROM AppUser WHERE username = ? AND user_password = ?";
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_id");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void save(User user) {
        String sql = "INSERT INTO AppUser (username, user_password, firstName, lastName, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
