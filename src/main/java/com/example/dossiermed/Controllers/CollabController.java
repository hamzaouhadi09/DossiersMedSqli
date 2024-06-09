package com.example.dossiermed.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collab")
@CrossOrigin("*")
public class CollabController {
    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }
}
