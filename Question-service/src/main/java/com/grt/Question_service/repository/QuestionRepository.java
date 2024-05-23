package com.grt.Question_service.repository;


import com.grt.Question_service.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
    List<QuestionEntity>findByCategory(String category);

    @Query(value = "SELECT q.id FROM question_entity q Where q.category=?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numsQ);
}
