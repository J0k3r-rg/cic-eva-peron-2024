package com.j0k3r_dev.cic_eva_peron.controllers.validationsLogin;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;

import java.util.Optional;

public interface ValidationsLogin {

    void validate(Optional<UserEntity> optional) throws UserException;

}
