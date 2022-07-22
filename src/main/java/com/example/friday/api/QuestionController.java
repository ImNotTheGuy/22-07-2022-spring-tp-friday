package com.example.friday.api;

import java.util.List;
import java.util.Map;

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
import com.example.friday.entity.Question;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @PostMapping("question")
    public void create(@RequestBody Question question) {
        questionService.create(question);
    }

    @GetMapping("question")
    public List<Question> findAll(){
        return questionService.findAll();
    }

    @GetMapping("question/{id}")
    public Map<String, List<String>> findAnswers(@PathVariable("id") long questionId){
        Question question = questionService.findById(questionId);
        return questionService.listAnswers(question);
    }

    @PatchMapping("question/{id}")
    public void update(@RequestBody Question question, @PathVariable("id") long id){
        questionService.update(question, id);
    }

    @DeleteMapping("question/{id}")
    public void deleteById(@PathVariable("id") long id){
        questionService.deleteById(id);
    }

}
