package com.example.friday.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friday.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}
