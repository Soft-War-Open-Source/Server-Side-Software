package com.appnutricare.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestAPIs {

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public String clientAccess(){
        return "Contenidos de Cliente!";
    }

    @GetMapping("/nutritionist")
    @PreAuthorize("hasRole('NUTRITIONIST') or hasRole('ADMIN')")
    public String receptionistAccess(){
        return "Contenidos de Nutricionista!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "Contenidos de Administrador!";
    }

}
