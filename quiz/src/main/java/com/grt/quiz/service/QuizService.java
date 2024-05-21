package com.grt.quiz.service;

import com.grt.quiz.entity.QuestionEntity;
import com.grt.quiz.entity.QuestionWrapper;
import com.grt.quiz.entity.QuizEntity;
import com.grt.quiz.entity.QuizResponse;
import com.grt.quiz.repository.QuestionRepository;
import com.grt.quiz.repository.QuizRepository;
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
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {
        List<QuestionEntity> questions = questionRepository.findRandomQuestionsByCategory(category,numsQ);

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setTitle(title);
        quizEntity.setQuestions(questions);
        quizRepository.save(quizEntity);
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

    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(Integer id){
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        if(!quiz.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<QuestionWrapper> questionsForUsers = quiz.get().getQuestions().stream()
                .map(q->new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> quizResponses) {
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        if (!quiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<QuestionEntity> questions = quiz.get().getQuestions();

        // Check if the number of responses matches the number of questions
        if (quizResponses.size() != questions.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int right = 0;
        for (int i = 0; i < quizResponses.size(); i++) {
            QuizResponse response = quizResponses.get(i);
            QuestionEntity question = questions.get(i);

            // Ensure both response and correct answer are not null before comparing
            if (response.getResponses() != null && question.getRightAnswer() != null &&
                    response.getResponses().trim().equalsIgnoreCase(question.getRightAnswer().trim())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
