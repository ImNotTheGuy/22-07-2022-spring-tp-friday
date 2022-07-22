package com.example.friday.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.friday.dao.QuestionRepository;
import com.example.friday.entity.Question;

@Service
public class QuestionService implements ServiceInterface<Question> {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void create(Question entity) {
        questionRepository.save(entity);
    }

    @Override
    public void deleteById(long id) {
        questionRepository.deleteById(id);

    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void update(Question entity) {
        questionRepository.save(entity);
    }
}
