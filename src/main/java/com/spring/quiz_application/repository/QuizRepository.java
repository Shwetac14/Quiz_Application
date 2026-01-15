package com.spring.quiz_application.repository;

import com.spring.quiz_application.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuizRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<QuizQuestion> getAllQuestions() {
        String sql = "SELECT * FROM quiz_question";
        return jdbcTemplate.query(sql, new QuizQuestionRowMapper());
    }

    public int addQuestion(QuizQuestion question) {
        String sql = "INSERT INTO quiz_question (question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                question.getQuestion(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD(),
                question.getCorrectOption());
    }

    private static class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
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
    }
}
