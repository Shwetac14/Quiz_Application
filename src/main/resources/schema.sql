CREATE DATABASE IF NOT EXISTS quizdb;
USE quizdb;

DROP TABLE IF EXISTS quiz_question;

CREATE TABLE quiz_question (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               question VARCHAR(255) NOT NULL,
                               option_a VARCHAR(100) NOT NULL,
                               option_b VARCHAR(100) NOT NULL,
                               option_c VARCHAR(100) NOT NULL,
                               option_d VARCHAR(100) NOT NULL,
                               correct_option VARCHAR(1) NOT NULL   -- store A, B, C, or D
);

CREATE TABLE quiz_result (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             username VARCHAR(100),
                             score INT,
                             total INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert a sample question
INSERT INTO quiz_question (question, option_a, option_b, option_c, option_d, correct_option)
VALUES ('What does HTML stand for?', 'Hyper Text Markup Language', 'Home Tool Markup Language', 'Hyperlinks and Text Markup Language', 'Hyper Text Machine Language', 'A');