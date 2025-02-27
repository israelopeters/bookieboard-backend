package com.example.bookieboard_backend.controller;

import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.model.QuestionDifficultyLevel;
import com.example.bookieboard_backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ap1/v1/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(
                questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/difficulty")
    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(
            @RequestParam QuestionDifficultyLevel difficultyLevel) {
        return new ResponseEntity<>(
                questionService.getAllQuestions(), HttpStatus.OK);
    }
}
