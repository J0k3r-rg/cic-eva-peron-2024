package com.j0k3r_dev.cic_eva_peron.controllers.MemberValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberException;
import org.springframework.stereotype.Component;

@Component
public class ValidateFormatIdentificationNumber implements ValidationMember{

    @Override
    public void validate(MemberRequest memberRequest) throws MemberException {
        if (!memberRequest.getIdentificationNumber().matches("[0-9]{8}")) {
            throw new MemberException("Formato de DNI incorrecto, 8 números sin puntos entre ellos",499);
        }
    }

}
