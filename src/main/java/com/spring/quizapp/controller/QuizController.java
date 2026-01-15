package com.spring.quizapp.controller;

import com.spring.quizapp.model.QuizQuestion;
import com.spring.quizapp.model.QuizSubmitRequest;
import com.spring.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuizQuestion>> getQuestions() {
        List<QuizQuestion> questions = quizService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion question) {
        if (question.getQuestion() == null || question.getCorrectOption() == null) {
            return ResponseEntity.badRequest().body("Question and correct option are required");
        }
        quizService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully");
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitQuiz(@RequestBody QuizSubmitRequest request) {
        if (request.getAnswers() == null || request.getAnswers().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Answers cannot be empty"));
        }

        int score = quizService.submitQuiz(request);
        int total = request.getAnswers().size();

        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }
}
