package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.controllers.registryValidations.ValidationsRegistry;
import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {

    @Autowired
    private UserService userService;

    @Autowired
    private List<ValidationsRegistry> validationsRegistries;

    @PreAuthorize("hasAuthority('Permission_REGISTRY_USER')")
    @PostMapping
    public ResponseEntity<?> registryUser(@RequestBody @Valid UserRequest userRequest) throws UserException {
        for(ValidationsRegistry validation : validationsRegistries){
            validation.Validate(userRequest);
        }
        return ResponseEntity.ok(userService.registryUser(userRequest));
    }

}
