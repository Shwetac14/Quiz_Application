package com.spring.quiz_application.controller;

import com.spring.quiz_application.model.QuizQuestion;
import com.spring.quiz_application.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
@Tag(name="Quiz Controller", description="APIs for Quiz Application")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) { this.service = service; }

    @GetMapping("/questions")
    @Operation(summary="Get all quiz questions", description="Returns all quiz questions without correct answers")
    public ResponseEntity<List<Map<String,Object>>> getQuestions() {
        return ResponseEntity.ok(service.getAllQuestions());
    }

    @PostMapping("/submit")
    @Operation(summary="Submit quiz answers")
    public ResponseEntity<Map<String,Object>> submitQuiz(@RequestBody Map<String,Map<Integer,String>> request) {
        Map<Integer,String> answers = request.get("answers");
        if (answers == null || answers.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","Invalid request"));
        return ResponseEntity.ok(service.submitQuiz(answers));
    }

    @PostMapping("/question")
    @Operation(summary="Add a quiz question")
    public ResponseEntity<Map<String,String>> addQuestion(@RequestBody QuizQuestion question) {
        if (question.getCorrectOption()==null || question.getCorrectOption().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("error","correctOption is required"));
        int rows = service.addQuestion(question);
        if (rows>0) return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Question added successfully"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Failed to add question"));
    }
}
