package com.example.friday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
    
    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Question() {
    }

    public Question(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question [answer=" + answer + ", id=" + id + ", question=" + question + "]";
    }

        


}
