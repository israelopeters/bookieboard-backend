package com.example.bookieboard_backend.repository;

import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.model.QuestionDifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficultyLevel(QuestionDifficultyLevel difficultyLevel);
}
