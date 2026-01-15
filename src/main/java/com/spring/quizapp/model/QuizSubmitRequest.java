package com.spring.quizapp.model;

import java.util.Map;

public class QuizSubmitRequest {
    private Map<String, String> answers; // questionId -> chosenOption

    public Map<String, String> getAnswers() { return answers; }
    public void setAnswers(Map<String, String> answers) { this.answers = answers; }
}