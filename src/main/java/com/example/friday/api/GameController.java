package com.example.friday.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.AnswerService;
import com.example.friday.business.QuestionService;
import com.example.friday.entity.Answer;
import com.example.friday.entity.Game;
import com.example.friday.entity.Question;

@RestController
public class GameController {


    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    
    @GetMapping("game/{questionId}")
    public Map<String, List<String>> newGame(@PathVariable("questionId") long questionId){

        Question question = questionService.findById(questionId);
        List<Answer> answerList = answerService.findAllByQuestion(question);
        List<String> answers = new ArrayList<>();
        for (Answer answer: answerList){
            answers.add(answer.getAnswer());
        }
        Map<String, List<String>> returnMap = new HashMap<>();
        returnMap.put(question.getQuestion(), answers);
        return returnMap;
    }

    @GetMapping("newGame/{questionId}/{answer}")
    public boolean yourAnswer(@PathVariable("questionId") long questionId, @PathVariable("answer") String answer){
        
        Question question = questionService.findById(questionId);
        List<Answer> answerList = answerService.findAllByQuestion(question);
        Game game = new Game(question, answerList);

        return game.getAnswerMap().get(answer) != null ? game.getAnswerMap().get(answer) : false;
    }


}
