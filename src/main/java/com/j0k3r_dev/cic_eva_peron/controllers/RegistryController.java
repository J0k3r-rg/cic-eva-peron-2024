package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registryUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.registryUser(userRequest));
    }

}
