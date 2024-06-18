package com.j0k3r_dev.cic_eva_peron.controllers.MemberValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberException;

public interface ValidationMember {

    void validate(MemberRequest memberRequest) throws MemberException;

    // Con solo implementar esta interface ya ejecuta el filtro de validacion
    // en el controlador sin necesidad de modificar nada <3

}
