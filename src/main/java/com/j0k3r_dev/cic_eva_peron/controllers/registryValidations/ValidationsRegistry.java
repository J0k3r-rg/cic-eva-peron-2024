package com.j0k3r_dev.cic_eva_peron.controllers.registryValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserException;

public interface ValidationsRegistry {

    void Validate(UserRequest userRequest) throws UserException;

}
