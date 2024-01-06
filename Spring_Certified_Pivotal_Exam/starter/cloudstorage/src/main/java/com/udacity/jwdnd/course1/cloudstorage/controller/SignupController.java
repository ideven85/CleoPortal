package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute UserEntity userEntity, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (!userService.isUsernameAvailable(userEntity.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(userEntity);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
           // model.addAttribute("signupSuccess", true);
            redirectAttributes.addFlashAttribute("message", "Signup successful , please login");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            return "redirect:/login";

        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}