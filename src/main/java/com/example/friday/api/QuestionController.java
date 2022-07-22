package com.example.friday.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.QuestionService;
import com.example.friday.entity.Question;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("question")
    public void create(@RequestBody Question question) {
        questionService.create(question);
    }

    @GetMapping("question")
    public List<Question> findAll(){
        return questionService.findAll();
    }

    @GetMapping("question/{id}")
    public Question findById(@PathVariable("id") long id){
        return questionService.findById(id);
    }

    @PatchMapping("question/{id}")
    public void update(@RequestBody Question question, @PathVariable("id") long id){
        Question currentQuestion = findById(id);
        currentQuestion.setQuestion(question.getQuestion());
        questionService.update(currentQuestion);
    }

}
