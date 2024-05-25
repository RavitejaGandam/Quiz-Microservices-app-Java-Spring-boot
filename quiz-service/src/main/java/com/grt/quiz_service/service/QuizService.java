package com.grt.quiz_service.service;


import com.grt.quiz_service.entity.QuestionEntity;
import com.grt.quiz_service.entity.QuestionWrapper;
import com.grt.quiz_service.entity.QuizEntity;
import com.grt.quiz_service.entity.Response;
import com.grt.quiz_service.feign.QuizInterface;
import com.grt.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numsQ).getBody();
        QuizEntity quiz = new QuizEntity();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }

//    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
//        Optional<QuizEntity> quiz = quizRepository.findById(id);
//        List<QuestionEntity> questionEntities = quiz.get().getQuestions();
//        List<QuestionWrapper>questionForUser = new ArrayList<>();
//        for(QuestionEntity q : questionEntities){
//            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            questionForUser.add(qw);
//        }
//        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
//    }

    // by using Stream API

//    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(Integer id){
//        Optional<QuizEntity> quiz = quizRepository.findById(id);
//        if(!quiz.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//       List<QuestionWrapper> questionsForUsers =
//                .map(q->new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
//    }


  //  public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        QuizEntity quiz = quizRepository.findById(id).get();
//        List<QuestionEntity> questions = quiz.getQuestions();
 //       int right = 0;
//        int i = 0;
//        for(Response response : responses){
//            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
//                right++;
//            System.out.println("value of right "+right);
//            i++;
//            System.out.println("value of i " + i);
//        }
  //      return new ResponseEntity<>(right, HttpStatus.OK);
    //}

































//    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        Optional<QuizEntity> optionalQuiz = quizRepository.findById(id);
//        if (!optionalQuiz.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        QuizEntity quiz = optionalQuiz.get();
//        List<QuestionEntity> questions = quiz.getQuestions();
//
//        // Ensure the responses list and questions list are of the same size
//        if (responses.size() != questions.size()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        int right = 0;
//        int i = 0;
//
//        for (Response response : responses) {
//            String responseValue = response.getResponse();
//            String rightAnswer = questions.get(i).getRightAnswer();
//
//            // Print values being compared for debugging
//            System.out.println("Response value: " + responseValue);
//            System.out.println("Right answer: " + rightAnswer);
//
//            // Ensure response value is compared correctly
//            if (responseValue != null && responseValue.equals(rightAnswer)) {
//                right++;
//                System.out.println("Right value: " + right);
//            }
//
//            i++;
//            System.out.println("I value: " + i);
//            System.out.println("Comparing Response: " + responseValue + " with Answer: " + rightAnswer);
//        }
//        return new ResponseEntity<>(right, HttpStatus.OK);
//    }


}
