package com.j0k3r_dev.cic_eva_peron.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class UserUtils {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Integer generateCodeNumberLogin(String username) throws UserException {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        Integer codeNumber = new Random().nextInt(100000000,999999999);
        userOptional.get().setCodeNumberLogin(codeNumber);
        return codeNumber;
    }

    public Integer obtainCodeNumberLogin(String id) throws UserException{
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        return userOptional.get().getCodeNumberLogin();
    }

    @Transactional
    public Integer generateCodeNumberActualization(String username) throws UserException {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        Integer codeNumber = new Random().nextInt(100000000,999999999);
        userOptional.get().setCodeNumberActualization(codeNumber);
        return codeNumber;
    }

    public Integer obtainCodeNumberActualization(String id) throws UserException {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        return userOptional.get().getCodeNumberActualization();
    }

}