package com.example.friday.api;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.AnswerService;
import com.example.friday.business.QuestionService;
import com.example.friday.entity.Answer;
import com.example.friday.entity.Question;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @PostMapping("answer")
    public void create(@RequestBody JSONObject jsonObject) {

        //TODO: CONTROLS

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

        answerService.create(answerToAdd);
    }

    @GetMapping("answer")
    public List<Answer> findAll(){
        return answerService.findAll();
    }

    @GetMapping("answer/{id}")
    public Answer findById(@PathVariable("id") long id){
        return answerService.findById(id);
    }

    @PatchMapping("answer/{id}")
    public void update(@RequestBody Answer answer, @PathVariable("id") long id){
        Answer currentAnswer = findById(id);
        currentAnswer.setAnswer(answer.getAnswer());
        currentAnswer.setCorrect(answer.isCorrect());
        currentAnswer.setQuestion(answer.getQuestion());
        answerService.update(currentAnswer);
    }

    @DeleteMapping("answer/{id}")
    public void deleteById(@PathVariable("id") long id){
        answerService.deleteById(id);
    }

}
