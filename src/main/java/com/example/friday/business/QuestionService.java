package com.example.friday.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.friday.dao.AnswerRepository;
import com.example.friday.dao.QuestionRepository;
import com.example.friday.entity.Answer;
import com.example.friday.entity.Question;

@Service
public class QuestionService implements ServiceInterface<Question> {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

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

    public void update(Question entity, long id) {

        Question currentQuestion = questionRepository.findById(id).get();
        currentQuestion.setQuestion(entity.getQuestion());
        questionRepository.save(entity);
    }

    public Map<String, List<String>> listAnswers(Question question) {

        Map<String, List<String>> returnMap = new HashMap<String, List<String>>();
        List<Answer> answers = answerRepository.findAllByQuestion(question);
        List<String> answersString = new ArrayList<>();

        for (Answer answer : answers) {
            answersString.add(answer.getAnswer());
        }

        returnMap.put(question.getQuestion(), answersString);

        return returnMap;
    }

    public Question findByQuestionQuestion(String question){
        return questionRepository.findQuestionByQuestion(question);
    }
}
