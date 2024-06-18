package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.controllers.MemberValidations.ValidationMember;
import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberException;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberService;
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
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private List<ValidationMember> validationList;

    @PreAuthorize("hasAuthority('Permission_CREATE_MEMBER')")
    @PostMapping("/create")
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberRequest memberRequest) throws MemberException {
        for(ValidationMember validation : validationList){
            validation.validate(memberRequest);
        }
        return ResponseEntity.ok(memberService.createAndSaveMember(memberRequest));
    }

}
