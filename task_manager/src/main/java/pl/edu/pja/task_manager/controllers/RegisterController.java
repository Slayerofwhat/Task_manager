package pl.edu.pja.task_manager.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.task_manager.models.dto.UserRegistrationDTO;
import pl.edu.pja.task_manager.services.UserService;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    String loginForm(Model model) {
        model.addAttribute("register", new UserRegistrationDTO());
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }

        userService.register(user);
        return "redirect:/";
    }
}
