package com.example.friday.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friday.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    public Question findQuestionByQuestion(String question);
}
