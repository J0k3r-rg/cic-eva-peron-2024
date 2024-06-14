package com.j0k3r_dev.cic_eva_peron.controllers.registryValidations;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistDNIInDB implements ValidationsRegistry{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void Validate(UserRequest userRequest) throws UserException {
        if(userRepository.existsByDni(userRequest.getDni())){
            throw new UserException("El DNI ya se encuentra registrado en la base de datos",494);
        }
    }
}
