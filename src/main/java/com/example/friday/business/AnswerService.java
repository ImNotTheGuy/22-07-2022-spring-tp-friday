package com.example.friday.business;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friday.dao.AnswerRepository;
import com.example.friday.entity.Answer;
import com.example.friday.entity.Question;

@Service
public class AnswerService implements ServiceInterface<Answer> {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionService questionService;

    public void create(JSONObject jsonObject) {

        Answer answerToAdd = new Answer();

        // Retrieve info from JsonObject
        String answer = jsonObject.get("answer").toString();
        boolean isCorrect = Boolean.parseBoolean(jsonObject.get("isCorrect").toString());

        // use the questionId field to retrieve question using questionService
        int questionId = Integer.parseInt(jsonObject.get("questionId").toString());
        Question question = questionService.findById(questionId);

        // set all fields to new answer object
        answerToAdd.setAnswer(answer);
        answerToAdd.setCorrect(isCorrect);
        answerToAdd.setQuestion(question);

        answerRepository.save(answerToAdd);

    }

    @Override
    public void deleteById(long id) {
        answerRepository.deleteById(id);

    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public void update(Answer entity, long id) {
        Answer currentAnswer = findById(id);
        currentAnswer.setAnswer(entity.getAnswer());
        currentAnswer.setCorrect(entity.isCorrect());
        currentAnswer.setQuestion(entity.getQuestion());
        answerRepository.save(currentAnswer);

    }

    @Override
    public Answer findById(long id) {
        return answerRepository.findById(id).get();
    }

    public List<Answer> findAllByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

}
