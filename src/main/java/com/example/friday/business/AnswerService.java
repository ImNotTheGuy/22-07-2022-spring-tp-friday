package com.example.friday.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.friday.dao.AnswerRepository;
import com.example.friday.entity.Answer;

public class AnswerService implements ServiceInterface<Answer>{

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public void create(Answer entity) {
        answerRepository.save(entity);
        
    }

    @Override
    public void deleteById(long id) {
        answerRepository.deleteById(id);
        
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(long id) {
        return answerRepository.findById(id).get();
    }

    @Override
    public void update(Answer entity) {
        answerRepository.save(entity);        
    }
    
    
}