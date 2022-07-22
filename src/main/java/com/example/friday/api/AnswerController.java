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

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @PostMapping("answer")
    public void create(@RequestBody JSONObject jsonObject) {
        answerService.create(jsonObject);

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
        
        answerService.update(answer, id);
    }

    @DeleteMapping("answer/{id}")
    public void deleteById(@PathVariable("id") long id){
        answerService.deleteById(id);
    }

}
