package com.grt.quiz.repository;

import com.grt.quiz.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Integer> {
}
