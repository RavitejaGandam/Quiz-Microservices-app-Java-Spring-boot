package com.grt.quiz.service;

import com.grt.quiz.entity.QuestionEntity;
import com.grt.quiz.entity.QuizEntity;
import com.grt.quiz.repository.QuestionRepository;
import com.grt.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {
        List<QuestionEntity> questions = questionRepository.findRandomQuestionsByCategory(category,numsQ);

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setTitle(title);
        quizEntity.setQuestions(questions);
        quizRepository.save(quizEntity);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }
}
