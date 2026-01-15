-- CREATE DATABASE IF NOT EXISTS quizdb;
-- USE quizdb;
--
-- CREATE TABLE IF NOT EXISTS quiz_question (
--                                              id INT AUTO_INCREMENT PRIMARY KEY,
--                                              question VARCHAR(255) NOT NULL,
--     option_a VARCHAR(100) NOT NULL,
--     option_b VARCHAR(100) NOT NULL,
--     option_c VARCHAR(100) NOT NULL,
--     option_d VARCHAR(100) NOT NULL,
--     correct_option CHAR(1) NOT NULL
--     );
--
-- CREATE TABLE IF NOT EXISTS quiz_result (
--                                            id INT AUTO_INCREMENT PRIMARY KEY,
--                                            username VARCHAR(100) NOT NULL,
--     score INT NOT NULL,
--     total INT NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--     );

CREATE TABLE IF NOT EXISTS quiz_question (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             question VARCHAR(255) NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL
    );


CREATE TABLE IF NOT EXISTS quiz_result (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           username VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    total INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

