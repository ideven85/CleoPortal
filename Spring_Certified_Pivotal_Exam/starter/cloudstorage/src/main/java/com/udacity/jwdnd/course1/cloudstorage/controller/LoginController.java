package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String loginView() {
        return "login";
    }

    //@GetMapping(value = "/logout")
    //public String logoutView() {
    //   return "login";
  //  }

}
