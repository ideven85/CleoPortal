package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
public class FilesController {
    private final UserService userService;
    private final FilesService filesService;

    public FilesController(UserService userService, FilesService filesService) {
        this.userService = userService;
        this.filesService = filesService;
    }

    @PostMapping(value = "/fileupload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, Principal principal, RedirectAttributes redirectAttributes) throws IOException {
        if(multipartFile.getSize()==0){
            redirectAttributes.addFlashAttribute("message", "Please Chose File");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/home";
        }
        else if(filesService.FileValidation(multipartFile,userService.getUserIdByUsername(principal.getName()))==false){
            redirectAttributes.addFlashAttribute("message", "The filename is already in use , try to change the name");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/home";
        }
        else if(filesService.checkFileSize(multipartFile)==false){
            redirectAttributes.addFlashAttribute("message", "file is too big , the maximum size is 1MB");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

            return "redirect:/home";
        }
        else filesService.uploadFile(userService.getUserIdByUsername(principal.getName()), multipartFile);
        redirectAttributes.addFlashAttribute("message", "file uploaded successfuly");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        return "redirect:/home";
    }

    @GetMapping("/deletefile/{id}")
    private String deleteFile(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes) {
        filesService.deletefilebyid(Math.toIntExact(id));
        redirectAttributes.addFlashAttribute("message", "File deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/home";

    }
}
