package com.grt.quiz_service.service;

import com.grt.quiz_service.entity.QuestionWrapper;
import com.grt.quiz_service.entity.QuizEntity;
import com.grt.quiz_service.entity.Response;
import com.grt.quiz_service.feign.QuizInterface;
import com.grt.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numsQ).getBody();
        QuizEntity quiz = new QuizEntity();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        List<Integer> questionsIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionsIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

    // public ResponseEntity<Integer> calculateResult(Integer id, List<Response>
    // responses) {
    // Optional<QuizEntity> optionalQuiz = quizRepository.findById(id);
    // if (!optionalQuiz.isPresent()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    //
    // QuizEntity quiz = optionalQuiz.get();
    // List<QuestionEntity> questions = quiz.getQuestions();
    //
    // // Ensure the responses list and questions list are of the same size
    // if (responses.size() != questions.size()) {
    // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
    //
    // int right = 0;
    // int i = 0;
    //
    // for (Response response : responses) {
    // String responseValue = response.getResponse();
    // String rightAnswer = questions.get(i).getRightAnswer();
    //
    // // Print values being compared for debugging
    // System.out.println("Response value: " + responseValue);
    // System.out.println("Right answer: " + rightAnswer);
    //
    // // Ensure response value is compared correctly
    // if (responseValue != null && responseValue.equals(rightAnswer)) {
    // right++;
    // System.out.println("Right value: " + right);
    // }
    //
    // i++;
    // System.out.println("I value: " + i);
    // System.out.println("Comparing Response: " + responseValue + " with Answer: "
    // + rightAnswer);
    // }
    // return new ResponseEntity<>(right, HttpStatus.OK);
    // }

}
