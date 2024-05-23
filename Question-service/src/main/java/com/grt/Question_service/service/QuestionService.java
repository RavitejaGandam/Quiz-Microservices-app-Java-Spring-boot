package com.grt.Question_service.service;


import com.grt.Question_service.entity.QuestionEntity;
import com.grt.Question_service.entity.QuestionWrapper;
import com.grt.Question_service.entity.Response;
import com.grt.Question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>>
    getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<QuestionEntity> questions = new ArrayList<>();
        for (Integer id : questionIds){
            questions.add(questionRepository.findById(id).get());
        }
        for (QuestionEntity question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer>
    getScore(List<Response> responses) {
        Integer right = 0;
        for(Response response : responses){
            QuestionEntity question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
