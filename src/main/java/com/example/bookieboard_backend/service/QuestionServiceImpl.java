package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.model.QuestionDifficultyLevel;
import com.example.bookieboard_backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByDifficultyLevel(QuestionDifficultyLevel difficultyLevel) {
        return questionRepository.findByDifficultyLevel(difficultyLevel);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}
