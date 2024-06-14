package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.response.PermissionResponse;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionException;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasAuthority('Permission_CREATE_PERMISSION')")
    @PostMapping("/save/{name}")
    public ResponseEntity<?> createPermission(@PathVariable @NotBlank String name, HttpServletRequest request) throws PermissionException {
        String token = jwtService.getTokenAuthorization(jwtService.getAuthorization(request));
        PermissionResponse permissionResponse = permissionService.createPermission(name.toUpperCase(),token);
        return ResponseEntity.ok(permissionResponse);
    }

    @PreAuthorize("hasAuthority('Permission_GET_PERMISSION')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllPermissions(){
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

}
