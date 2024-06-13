package com.j0k3r_dev.cic_eva_peron.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {

    @PostMapping
    public ResponseEntity<?> registryUser(){
        return ResponseEntity.ok().build();
    }

}
