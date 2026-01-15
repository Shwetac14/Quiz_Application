package com.spring.quiz_application.service;

import com.spring.quiz_application.model.QuizQuestion;
import com.spring.quiz_application.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) { this.repository = repository; }

    public List<Map<String,Object>> getAllQuestions() {
        List<QuizQuestion> questions = repository.getAllQuestions();
        List<Map<String,Object>> response = new ArrayList<>();
        for (QuizQuestion q : questions) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", q.getId());
            map.put("question", q.getQuestion());
            map.put("options", Arrays.asList(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()));
            response.add(map);
        }
        return response;
    }

    public int addQuestion(QuizQuestion question) { return repository.addQuestion(question); }

    public Map<String,Object> submitQuiz(Map<Integer,String> answers) {
        List<QuizQuestion> questions = repository.getAllQuestions();
        int score=0;
        int total=questions.size();

        Map<Integer,String> correctMap = new HashMap<>();
        for (QuizQuestion q: questions) { correctMap.put(q.getId(), q.getCorrectOption()); }

        for (Map.Entry<Integer,String> entry: answers.entrySet()) {
            if (correctMap.containsKey(entry.getKey()) &&
                    correctMap.get(entry.getKey()).equalsIgnoreCase(entry.getValue())) score++;
        }

        return Map.of("score", score, "total", total);
    }
}
