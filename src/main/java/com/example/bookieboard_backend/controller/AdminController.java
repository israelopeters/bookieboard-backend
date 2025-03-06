package com.example.bookieboard_backend.controller;

import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/questions")
    public String reviews(Model model) {
        List<Question> questionList = new ArrayList<>(questionService.getAllQuestions());
        model.addAttribute("questionList", questionList);
        return "questions";
    }

    @GetMapping("questions/add")
    public String showNewQuestionForm(Model model) {
        Question question = new Question();
        model.addAttribute("newQuestion", question);
        return "add_question";
    }

    @PostMapping("/questions/add/save")
    public String addNewQuestion(
            @ModelAttribute("newQuestion") Question question, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "Error";
        }
        questionService.addQuestion(question);
        return "redirect:/questions"; // Redirect to endpoint instead of template so as to load updated reviews list
    }
}
