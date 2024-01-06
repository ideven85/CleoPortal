package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ErrorHandledController implements ErrorController {

    @RequestMapping(value = "/error")
    public String errorHandler(Principal principal, RedirectAttributes redirectAttributes){
        if(principal==null){
            redirectAttributes.addFlashAttribute("message", "You have entered a wrong page , you have been redirected to the Login page");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

            return "redirect:/login";
        }else{
            redirectAttributes.addFlashAttribute("message", "You have entered a wrong page , you have been redirected to the Home page");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/home";}
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}