package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.model.QuestionDifficultyLevel;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    List<Question> getQuestionsByDifficultyLevel(QuestionDifficultyLevel difficultyLevel);
}
