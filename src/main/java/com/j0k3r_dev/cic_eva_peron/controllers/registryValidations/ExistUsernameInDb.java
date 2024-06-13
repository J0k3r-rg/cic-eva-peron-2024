package com.j0k3r_dev.cic_eva_peron.controllers.registryValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistUsernameInDb implements ValidationsRegistry{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void Validate(UserRequest userRequest) throws UserException {
        if(userRepository.existsByUsername(userRequest.getUsername()))
            throw new UserException("El usuario ya se encuentra registrado",492);
    }
}
