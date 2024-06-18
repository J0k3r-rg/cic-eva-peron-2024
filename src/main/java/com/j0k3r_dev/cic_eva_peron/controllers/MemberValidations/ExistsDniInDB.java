package com.j0k3r_dev.cic_eva_peron.controllers.MemberValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberException;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsDniInDB implements ValidationMember{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void validate(MemberRequest memberRequest) throws MemberException {
        if(memberRepository.existsByIdentificationNumber(memberRequest.getIdentificationNumber())){
            throw new MemberException("El DNI ya se encuentra registrado",496);
        }
    }
}
