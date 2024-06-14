package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.request.RoleRequest;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('Permission_CREATE_ROLE')")
    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleRequest role){
        return ResponseEntity.ok(
                roleService.createRole(role)
        );
    }

    @PreAuthorize("hasAuthority('Permission_GET_ROLE')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
