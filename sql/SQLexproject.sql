CREATE DATABASE IF NOT EXISTS examProject_DB;

USE examProject_DB;

SHOW TABLES;

ALTER TABLE Subproject
    MODIFY COLUMN subProjectName VARCHAR(255) NULL;

DROP PROCEDURE IF EXISTS AddColumnIfNotExists;

DELIMITER //
CREATE PROCEDURE AddColumnIfNotExists(
    IN table_name VARCHAR(64),
    IN column_name VARCHAR(64),
    IN column_definition VARCHAR(255)
)
BEGIN
    DECLARE column_exists INT;

    SELECT COUNT(*)
    INTO column_exists
    FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = table_name
      AND COLUMN_NAME = column_name;

    IF column_exists = 0 THEN
        SET @alter_table_sql = CONCAT('ALTER TABLE ', table_name, ' ADD COLUMN ', column_name, ' ', column_definition);
        PREPARE stmt FROM @alter_table_sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;
END //
DELIMITER ;

CALL AddColumnIfNotExists('Subproject', 'subProjectStatus', 'VARCHAR(50)');
CALL AddColumnIfNotExists('Task', 'timeEstimate', 'INT');

CREATE TABLE IF NOT EXISTS Project (
                                       projectId INT PRIMARY KEY AUTO_INCREMENT,
                                       projectName VARCHAR(255) NOT NULL,
                                       projectDescription TEXT,
                                       projectStartDate DATE,
                                       projectEndDate DATE,
                                       projectStatus VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Subproject (
                                          subProjectId INT PRIMARY KEY AUTO_INCREMENT,
                                          subProjectName VARCHAR(255) NOT NULL,
                                          subProjectDescription TEXT,
                                          subProjectStartDate DATE,
                                          subProjectEndDate DATE,
                                          subProjectStatus VARCHAR(50),
                                          projectId INT,
                                          FOREIGN KEY (projectId) REFERENCES Project(projectId)
);

CREATE TABLE IF NOT EXISTS Task (
                                    taskId INT PRIMARY KEY AUTO_INCREMENT,
                                    taskName VARCHAR(255) NOT NULL,
                                    taskDescription TEXT,
                                    taskStartDate DATE,
                                    taskEndDate DATE,
                                    subProjectId INT,
                                    timeEstimate INT,
                                    FOREIGN KEY (subProjectId) REFERENCES Subproject(subProjectId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS AppUser; -- Ensure the table is dropped first to avoid conflicts
CREATE TABLE IF NOT EXISTS AppUser (
                                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                                       username VARCHAR(255) NOT NULL,
                                       user_password VARCHAR(255) NOT NULL,
                                       firstName VARCHAR(255),
                                       lastName VARCHAR(255),
                                       email VARCHAR(255),
                                       role VARCHAR(50)
);

INSERT INTO Project (projectName, projectDescription, projectStartDate, projectEndDate, projectStatus)
VALUES ('Project1', 'Description1', '2024-01-01', '2024-12-31', 'active');

INSERT INTO Subproject (subProjectName, subProjectDescription, subProjectStartDate, subProjectEndDate, subProjectStatus, projectId)
VALUES ('Subproject1', 'Description1', '2024-01-01', '2024-06-30', 'active', 1);

INSERT INTO Task (taskName, taskDescription, taskStartDate, taskEndDate, subProjectId, timeEstimate)
VALUES ('Task1', 'Description1', '2024-01-01', '2024-01-31', 1, 5);

INSERT INTO AppUser (username, user_password, firstName, lastName, email, role)
VALUES ('admin', 'password', 'Admin', 'User', 'admin@example.com', 'admin');
