package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.request.report.ReportRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @PreAuthorize("hasAuthority('Permission_CREATE_REPORT')")
    @PostMapping("/create")
    public ResponseEntity<?> createReport(@RequestBody @Valid ReportRequest reportRequest){

        return ResponseEntity.ok(
                "success"
        );
    }

}
