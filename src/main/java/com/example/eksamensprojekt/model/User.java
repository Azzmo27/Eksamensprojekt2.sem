package com.example.eksamensprojekt.model;

public class User {
    private String username;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private int userId;

    public User() {}

    public User(String username, String userPassword, String firstName, String lastName, String email, String role, int userId) {
        this.username = username;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }


    public String getUserPassword() {
        return userPassword;
    }



    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



    public String getEmail() {
        return email;
    }


    public String getRole() {
        return role;
    }
    public int getUserId(){
        return userId;
    }

}



