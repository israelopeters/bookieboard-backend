package com.example.bookieboard_backend.controller;

import com.example.bookieboard_backend.model.DtoMapper;
import com.example.bookieboard_backend.model.Question;
import com.example.bookieboard_backend.model.Role;
import com.example.bookieboard_backend.model.User;
import com.example.bookieboard_backend.model.dto.UserCreationDto;
import com.example.bookieboard_backend.repository.UserRepository;
import com.example.bookieboard_backend.service.QuestionService;
import com.example.bookieboard_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    DtoMapper dtoMapper;

    @GetMapping("/admin/signup")
    public String showAdminSignupForm(Model model) {
        User user = new User();
        model.addAttribute("adminUserForSignup", user);
        return "admin_signup";
    }

    @PostMapping("/admin/signup/save")
    public String signup(@Valid @ModelAttribute("adminUserForSignup") UserCreationDto userCreationDto,
                         BindingResult bindingResult, Model model) {
        if(isUserExists(dtoMapper.toUser(userCreationDto))) {
            bindingResult.rejectValue(
                    "email", "", "This email is already registered to an admin user.");
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("adminUserForSignup", userCreationDto);
            return "admin_signup";
        }
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        User user = dtoMapper.toUser(userCreationDto);
        user.setRoles(List.of(role));
        userService.addUser(user);
        return "admin_signup_success";
    }

    @GetMapping("/admin/questions")
    public String reviews(Model model) {
        List<Question> questionList = new ArrayList<>(questionService.getAllQuestions());
        model.addAttribute("questionList", questionList);
        return "questions";
    }

    @GetMapping("/admin/questions/add")
    public String showNewQuestionForm(Model model) {
        Question question = new Question();
        model.addAttribute("newQuestion", question);
        return "add_question";
    }

    @PostMapping("/admin/questions/add/save")
    public String addNewQuestion(
            @ModelAttribute("newQuestion") Question question, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "Error";
        }
        questionService.addQuestion(question);
        return "redirect:/admin/questions"; // Redirect to endpoint instead of template so as to load updated reviews list
    }

    private Boolean isUserExists(User user) {
        String email = user.getEmail();
        Optional<User> existingUserCheck = userRepository.findByEmail(email);
        boolean userStatus = false;

        if (existingUserCheck.isPresent()) {
            userStatus = true;
            User existingUserPresent = existingUserCheck.get();
            userStatus = (existingUserPresent.getEmail() != null &&
                    !existingUserPresent.getEmail().isEmpty());
        }
        return userStatus;
    }
}
