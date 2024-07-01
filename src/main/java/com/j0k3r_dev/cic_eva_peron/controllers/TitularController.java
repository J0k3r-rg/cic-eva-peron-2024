package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.controllers.TitutarValidations.TitularValidation;
import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularException;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularService;
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
@RequestMapping("/api/v1/titular")
public class TitularController {

    @Autowired
    private TitularService titularService;

    @Autowired
    private List<TitularValidation> titularValidationList;

    @PreAuthorize("hasAuthority('Permission_CREATE_TITULAR')")
    @PostMapping("/create")
    public ResponseEntity<?> createAndSaveTitular(@RequestBody @Valid TitularRequest titularRequest) throws TitularException {

        for (TitularValidation titularValidation : titularValidationList) {
            titularValidation.validate(titularRequest);
        }
        return ResponseEntity.ok(
                titularService.registryTitular(titularRequest)
        );
    }

    @PreAuthorize("hasAuthority('Permission_UPDATE_TITULAR')")
    @PostMapping("/update")
    public ResponseEntity<?> updateTitularAndSave(@RequestBody @Valid TitularRequest titularRequest) throws TitularException {
        return ResponseEntity.ok(
                titularService.updateTitular(titularRequest)
        );
    }

}
