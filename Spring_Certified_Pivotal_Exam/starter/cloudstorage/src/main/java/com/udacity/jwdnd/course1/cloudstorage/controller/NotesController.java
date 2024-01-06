package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller

public class NotesController {
    private final UserService userService;
    private final NotesService notesService;

    public NotesController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }

    @PostMapping(value = "/newnote")
    public String newNote(@ModelAttribute NotesEntity notesEntity, Principal principal , RedirectAttributes redirectAttributes) throws IOException {

        if(notesEntity.getNoteId()!=null){
            notesService.updateNote(notesEntity);
            redirectAttributes.addFlashAttribute("message", "Note Updated");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        }
        else{   notesService.newNote(userService.getUserIdByUsername(principal.getName()),notesEntity);
            redirectAttributes.addFlashAttribute("message", "Note Added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        }

        return "redirect:/home";
    }
    @GetMapping("/deletenote/{id}")
    private String deleteNote(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes) {
        notesService.deleteNotebyid(Math.toIntExact(id));
        redirectAttributes.addFlashAttribute("message", "Note Deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/home";
    }
}
