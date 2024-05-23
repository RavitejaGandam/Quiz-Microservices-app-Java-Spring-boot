package com.grt.Question_service.service;


import com.grt.Question_service.entity.QuestionEntity;
import com.grt.Question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<QuestionEntity> getAllQuestions() {
       return questionRepository.findAll();
    }

    public List<QuestionEntity> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public QuestionEntity addQuestion(QuestionEntity questionEntity) {
        return questionRepository.save(questionEntity);
    }
}
