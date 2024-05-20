package com.grt.quiz.repository;

import com.grt.quiz.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
    List<QuestionEntity>findByCategory(String category);
}
