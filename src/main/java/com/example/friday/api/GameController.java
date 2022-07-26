package com.example.friday.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.AnswerService;
import com.example.friday.business.PlayerService;
import com.example.friday.business.QuestionService;
import com.example.friday.entity.Answer;
import com.example.friday.entity.Game;
import com.example.friday.entity.Player;
import com.example.friday.entity.Question;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RestController
public class GameController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;


    @Autowired
    PlayerService playerService;

    @GetMapping("game/{playerId}/{questionId}")
    public Map<String, List<String>> newGame(@PathVariable("questionId") long questionId, @PathVariable("playerId") long playerId) {
        String answerUrl = "";
        Question question = questionService.findById(questionId);
        List<Answer> answerList = answerService.findAllByQuestion(question);
        List<String> answers = new ArrayList<>();
        for (Answer answer : answerList) {
            answerUrl = String.format("%s: http://localhost:8080/game/%d/%d/%s", 
            answer.getAnswer(),
            playerId,
            questionId,
            answer.getAnswer());
            // answers.add(answer.getAnswer());
            answers.add(answerUrl);
        }
        Map<String, List<String>> returnMap = new HashMap<>();
        returnMap.put(question.getQuestion(), answers);
        return returnMap;
    }

    @GetMapping("game/{playerId}/{questionId}/{answer}")
    public boolean yourAnswer(@PathVariable("playerId") long playerId, @PathVariable("questionId") long questionId, @PathVariable("answer") String answer) {
        Player player = playerService.findById(playerId);
        Question question = questionService.findById(questionId);
        List<Answer> answerList = answerService.findAllByQuestion(question);
        Game game = new Game(question, answerList);
        
        if ( game.getAnswerMap().get(answer) != null){
            if (game.getAnswerMap().get(answer)){
                player.setScore(player.getScore() + 1);
                playerService.update(playerId, player);
            }
        }
        return game.getAnswerMap().get(answer) != null ? game.getAnswerMap().get(answer) : false;
    }

    // get a rdandom game. This returns a map that comprises of the question, and it's list of answers
    @GetMapping("game/random/{playerId}")
    public Map<String, List<String>> randomGame(@PathVariable("playerId") long playerId) {

        // Create an array comprising of all questions (id)
        List<Long> questionIds = new ArrayList<>();
        for (Question question : questionService.findAll()) {
            questionIds.add(question.getId());
        }

        // this will get a random question id from the above list
        long questionId = questionIds.get(new Random().nextInt(questionIds.size()));
        // get the question corresponding to id above
        Question question = questionService.findById(questionId);

        String answerUrl = "";
        // find all answers corresponding to the question
        List<Answer> answerList = answerService.findAllByQuestion(question);

        // loop through the Answer List and create an array that only takes the answer as string
        List<String> answers = new ArrayList<>();
        for (Answer answer : answerList) {
            // here we add the url so user can simply click one of the answers. the URL refers to the "yourAnswer" method
            answerUrl = String.format("%s: http://localhost:8080/game/%d/%d/%s",
            answer.getAnswer(),
            playerId,
            questionId,
            answer.getAnswer());
            // answers.add(answer.getAnswer());
            answers.add(answerUrl);
        }
        // Create and return the HashMap with the question and it's answers.
        Map<String, List<String>> returnMap = new HashMap<>();
        returnMap.put(question.getQuestion(), answers);
        return returnMap;
    }

    @PostMapping("newGame")
    public void createNewGame(@RequestBody String jsonString){

        // read JSON object
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject(); //FIXME: understand what to use to avoid deprecation warning

        // get question 
        String questionString = jsonObject.get("question").getAsString();

        // create the new question and get it's id
        Question question = new Question();
        question.setQuestion(questionString);
        questionService.create(question);
        long questionId = questionService.findByQuestionQuestion(questionString).getId();

        // get the json array with the list of answers for this question
        JsonArray answerArray = jsonObject.get("answers").getAsJsonArray();

        // loop through the answers, and foreach answer set the question id to the question above
        for (JsonElement jsonElement: answerArray){
            
            String answerString = jsonElement.getAsJsonObject().get("answer").getAsString();
            Boolean isCorrect = jsonElement.getAsJsonObject().get("isCorrect").getAsBoolean();

            // FIXME: this is a bit stupid, as we use 2 different libraries for parsing json
            JSONObject jsonAnswer = new JSONObject();

            HashMap<String, Object> answerMap = new HashMap<>();

            answerMap.put("answer", answerString);
            answerMap.put("isCorrect", isCorrect);
            answerMap.put("questionId", questionId);

            jsonAnswer = new JSONObject(answerMap);

            // jsonAnswer.put("answer", answerString);
            // jsonAnswer.put("isCorrect", isCorrect);
            // jsonAnswer.put("questionId", questionId);

            answerService.create(jsonAnswer);
        }
        
    }

}
