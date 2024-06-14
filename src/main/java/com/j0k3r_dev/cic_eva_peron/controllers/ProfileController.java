package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.request.ProfileRequest;
import com.j0k3r_dev.cic_eva_peron.profile.ProfileException;
import com.j0k3r_dev.cic_eva_peron.profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PreAuthorize("hasAuthority('Permission_UPDATE_PROFILE')")
    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid ProfileRequest profileRequest) throws ProfileException {
        return ResponseEntity.ok(profileService.updateProfile(profileRequest));
    }

}
