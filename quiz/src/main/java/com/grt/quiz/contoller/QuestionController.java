package com.grt.quiz.contoller;

import com.grt.quiz.entity.QuestionEntity;
import com.grt.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
