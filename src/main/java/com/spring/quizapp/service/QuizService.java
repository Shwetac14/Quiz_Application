package com.spring.quizapp.service;

import com.spring.quizapp.model.QuizQuestion;
import com.spring.quizapp.model.QuizSubmitRequest;
import com.spring.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questions = quizRepository.getAllQuestions();
        for (QuizQuestion q : questions) {
            q.setCorrectOption(null); // hide correct answer
        }
        return questions;
    }

    public void addQuestion(QuizQuestion question) {
        quizRepository.addQuestion(question);
    }

    public int submitQuiz(QuizSubmitRequest request) {
        Map<String, String> answers = request.getAnswers();
        if (answers == null) throw new RuntimeException("Answers cannot be null");

        int score = 0;
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            try {
                int questionId = Integer.parseInt(entry.getKey());
                String userAnswer = entry.getValue();

                Optional<QuizQuestion> qOptional = quizRepository.getQuestionById(questionId);
                if (qOptional.isEmpty()) continue; // skip invalid IDs

                QuizQuestion q = qOptional.get();
                if (q.getCorrectOption().equalsIgnoreCase(userAnswer)) {
                    score++;
                }
            } catch (NumberFormatException e) {
                // skip invalid keys
            }
        }
        return score;
    }
}
