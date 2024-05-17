    DROP DATABASE IF EXISTS ExamProject_DB;
    CREATE DATABASE IF NOT EXISTS ExamProject_DB;
    USE ExamProject_DB;

    CREATE TABLE IF NOT EXISTS project (
    projectId INT AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    startDate DATE,
    endDate DATE
    );

    CREATE TABLE IF NOT EXISTS subProject (
    subProjectId INT AUTO_INCREMENT PRIMARY KEY,
    subProjectName VARCHAR(255),
    subProjectDescription VARCHAR(255),
    subProjectStartDate DATE,
    subProjectEndDate DATE,
    subProjectStatus VARCHAR(255),
    projectId INT,
    FOREIGN KEY (projectId) REFERENCES project(projectId)
    );
    CREATE TABLE IF NOT EXISTS task (
    taskId INT AUTO_INCREMENT PRIMARY KEY,
    taskName VARCHAR(255),
    taskDescription VARCHAR(255),
    taskStartDate DATE,
    taskEndDate DATE,
    timeEstimate INT,
    subProjectId INT,
    FOREIGN KEY (subProjectId) REFERENCES subProject(subProjectId)
    );

    CREATE TABLE IF NOT EXISTS user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255)
    );

    ALTER TABLE task ADD COLUMN subProjectId INT;
