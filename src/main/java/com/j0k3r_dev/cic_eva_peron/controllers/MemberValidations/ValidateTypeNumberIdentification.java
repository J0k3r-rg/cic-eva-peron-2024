package com.j0k3r_dev.cic_eva_peron.controllers.MemberValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentification;
import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentificationRepository;
import com.j0k3r_dev.cic_eva_peron.report.member.MemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateTypeNumberIdentification implements ValidationMember{

    @Autowired
    private TypeOfIdentificationRepository repository;

    @Override
    public void validate(MemberRequest memberRequest) throws MemberException {
        String type = memberRequest.getTypeOfIdentificationString();
        List<TypeOfIdentification> typeOfIdentifications = repository.findAll();
        for (TypeOfIdentification typeOfIdentification : typeOfIdentifications) {
            if (typeOfIdentification.getName().equals(type)) {
                return;
            }
        }
        throw new MemberException("El tipo de documento que quiere agregar no existe en la base de datos", 499);
    }

}
