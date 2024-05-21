package com.grt.quiz.repository;

import com.grt.quiz.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
    List<QuestionEntity>findByCategory(String category);

    @Query(value = "SELECT * FROM question_entity q Where q.category=?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
    List<QuestionEntity> findRandomQuestionsByCategory(String category, int numsQ);
}
