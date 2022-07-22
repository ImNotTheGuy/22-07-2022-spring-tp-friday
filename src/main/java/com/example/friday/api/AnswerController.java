package com.example.friday.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.AnswerService;
import com.example.friday.entity.Answer;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("answer")
    public void create(@RequestBody Answer answer) {
        answerService.create(answer);
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

}
