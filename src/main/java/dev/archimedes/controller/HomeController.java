package dev.archimedes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/oauth")
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        return "Welcome! Home";
    }

    @GetMapping("/github")
    public String getGithub(Principal authentication){
        return authentication.getName();
    }
}
