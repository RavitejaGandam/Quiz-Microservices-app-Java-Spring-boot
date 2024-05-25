package com.grt.quiz_service.repository;


import com.grt.quiz_service.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Integer> {
}
