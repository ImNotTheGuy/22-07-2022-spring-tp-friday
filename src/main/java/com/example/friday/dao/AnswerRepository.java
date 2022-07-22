package com.example.friday.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friday.entity.Answer;
import com.example.friday.entity.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    public List<Answer> findAllByQuestion(Question question);

}
