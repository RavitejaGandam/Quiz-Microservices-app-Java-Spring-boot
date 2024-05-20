package com.grt.quiz.service;

import com.grt.quiz.entity.QuestionEntity;
import com.grt.quiz.repository.QuestionRepository;
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
}
