package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller

public class HomeController {
  //  private final UserService userService;
    private final UserService userService;
    private final FilesService filesService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;
    public HomeController(UserService userService, FilesService filesService, NotesService notesService, CredentialsService credentialsService) {
        this.userService = userService;
        this.filesService = filesService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
    }

    @GetMapping(value = "/home")
    public String homeView(Model model, Principal principal) {
        model.addAttribute("fileslist",filesService.getFilesByUserId(userService.getUserIdByUsername(principal.getName())));
        model.addAttribute("noteslist",notesService.getNotesByUserId(userService.getUserIdByUsername(principal.getName())));
        model.addAttribute("credentialslist",credentialsService.getAllCredential(userService.getUserIdByUsername(principal.getName())));
        return "home";
    }










}
