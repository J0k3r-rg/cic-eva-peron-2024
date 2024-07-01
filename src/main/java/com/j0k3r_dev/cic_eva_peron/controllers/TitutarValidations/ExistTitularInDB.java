package com.j0k3r_dev.cic_eva_peron.controllers.TitutarValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularException;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistTitularInDB implements TitularValidation{

    @Autowired
    private TitularRepository titularRepository;

    @Override
    public void validate(TitularRequest titularRequest) throws TitularException {
        if (titularRepository.existsByIdentificationNumber(titularRequest.getIdentificationNumber()))
            throw new TitularException("Ya se encuentra un titular registrado con ese DNI",492);
    }
}
