package com.example.friday.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private String question;
    private Map<String, Boolean> answerMap = new HashMap<>();

    public Game() {
    }

    public Game(Question question, List<Answer> answerList) {
        this.question = question.getQuestion();
        for (Answer answer: answerList){
            this.answerMap.put(answer.getAnswer(), answer.isCorrect());
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Boolean> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<String, Boolean> answerMap) {
        this.answerMap = answerMap;
    }

    @Override
    public String toString() {
        return "Game [answerMap=" + answerMap + ", question=" + question + "]";
    }

    

    

}
