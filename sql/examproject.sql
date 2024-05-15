CREATE DATABASE IF NOT EXISTS ExamProject_DB;
CREATE SCHEMA IF NOT EXISTS ExamProject_DB;
USE ExamProject_DB;

CREATE TABLE IF NOT EXISTS project (
    projectId INT AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    startDate Date,
    endDate date
);

CREATE TABLE IF NOT EXISTS subProject (
    subProject_id INT AUTO_INCREMENT PRIMARY KEY,
    subProjectName VARCHAR(255),
    subProjectDescription VARCHAR(255),
    subProjectStartDate Date,
    subProjectEndDate Date,
    subProjectStatus VARCHAR(255),
    projectId INT,
    FOREIGN KEY (projectId) REFERENCES project (projectId)
    );

CREATE TABLE IF NOT EXISTS task (
    Task_id INT AUTO_INCREMENT PRIMARY KEY,
    taskName VARCHAR(255),
    taskDescription VARCHAR(255),
    taskStartDate Date,
    taskEndDate Date,
    timeEstimate INT,
    subProject_id INT,
    FOREIGN KEY (subProject_id) REFERENCES subProject(subProject_id)
);
