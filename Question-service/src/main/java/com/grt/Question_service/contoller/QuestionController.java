package com.grt.Question_service.contoller;


import com.grt.Question_service.entity.QuestionEntity;
import com.grt.Question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public List<QuestionEntity> getAllQuestions(){
       return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<QuestionEntity> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<QuestionEntity> addQuestion(@RequestBody QuestionEntity questionEntity){
         return ResponseEntity.ok().body(questionService.addQuestion(questionEntity));
    }

}
