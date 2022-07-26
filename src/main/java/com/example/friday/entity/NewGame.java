package com.example.friday.entity;

import java.util.Map;

public class NewGame {

    private String question;
    private Map<String, Boolean>[] answerListMap;

    
    public NewGame() {
    }

    public NewGame(String question, Map<String, Boolean>[] answerListMap) {
        this.question = question;
        this.answerListMap = answerListMap;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Boolean>[] getAnswerListMap() {
        return answerListMap;
    }

    public void setAnswerListMap(Map<String, Boolean>[] answerListMap) {
        this.answerListMap = answerListMap;
    }

    @Override
    public String toString() {
        return "NewGame [answerListMap=" + answerListMap + ", question=" + question + "]";
    }

    


}
