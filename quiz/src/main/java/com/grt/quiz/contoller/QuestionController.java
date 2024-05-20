package com.grt.quiz.contoller;

import com.grt.quiz.entity.QuestionEntity;
import com.grt.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    public List<QuestionEntity> getAllQuestions(){
       return questionService.getAllQuestions();
    }
}
