package com.spring.quizapp.repository;
import com.spring.quizapp.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map DB rows to QuizQuestion objects
    private RowMapper<QuizQuestion> rowMapper = new RowMapper<>() {
        @Override
        public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuizQuestion(
                    rs.getInt("id"),
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_option")
            );
        }
    };

    // Fetch all questions
    public List<QuizQuestion> getAllQuestions() {
        String sql = "SELECT * FROM quiz_question";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Fetch question by ID
    public Optional<QuizQuestion> getQuestionById(int id) {
        String sql = "SELECT * FROM quiz_question WHERE id = ?";
        List<QuizQuestion> list = jdbcTemplate.query(sql, rowMapper, id);
        if (list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    // Insert new question
    public int addQuestion(QuizQuestion question) {
        String sql = "INSERT INTO quiz_question (question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                question.getQuestion(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD(),
                question.getCorrectOption()
        );
    }
}