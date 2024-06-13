package com.j0k3r_dev.cic_eva_peron.controllers.validationsLogin;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IsUserEnable implements ValidationsLogin{
    @Override
    public void validate(Optional<UserEntity> optional) throws UserException {
        if(!optional.get().getEnable())
            throw new UserException("El usuario no se encuentra habilitado, verifique su email para activar la cuenta",491);
    }
}
