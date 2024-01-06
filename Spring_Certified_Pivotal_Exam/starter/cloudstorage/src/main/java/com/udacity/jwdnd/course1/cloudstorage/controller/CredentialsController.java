package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
public class CredentialsController {
    private final UserService userService;
    private final CredentialsService credentialsService;

    public CredentialsController(UserService userService, CredentialsService credentialsService) {
        this.userService = userService;
        this.credentialsService = credentialsService;
    }

    @PostMapping(value = "/newcredential")
    public String newCredential(@ModelAttribute CredentialsEntity credentialsEntity, Principal principal, RedirectAttributes redirectAttributes) throws IOException {

        if(credentialsEntity.getCredentialId()!=null) {
            credentialsService.updateCredential(credentialsEntity);
            redirectAttributes.addFlashAttribute("message", "Credential Updated");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        }
        else if (credentialsService.credentialValidation(credentialsEntity, userService.getUserIdByUsername(principal.getName()))==false){
            redirectAttributes.addFlashAttribute("message", "There is already a Username in this URL");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

            return "redirect:/home";
        }
        else{
            credentialsService.newCredential(userService.getUserIdByUsername(principal.getName()), credentialsEntity);
            redirectAttributes.addFlashAttribute("message", "Credential Added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        }
        return "redirect:/home";
    }
    @GetMapping("/deletecredential/{id}")
    private String deleteCredential(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes) {
        credentialsService.deleteCredentialById(Math.toIntExact(id));
        redirectAttributes.addFlashAttribute("message", "Credential Deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/home";
    }

}
