package com.j0k3r_dev.cic_eva_peron.controllers.TitutarValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.users.titular.TitularException;

public interface TitularValidation {

    void validate(TitularRequest titularRequest) throws TitularException;

}
