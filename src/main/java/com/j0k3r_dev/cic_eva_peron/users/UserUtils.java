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
    public Integer generateCodeNumber(String username) throws UserException {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        Integer codeNumber = new Random().nextInt(100000000,999999999);
        userOptional.get().setCodeNumber(codeNumber);
        userRepository.save(userOptional.get());
        return codeNumber;
    }

    public Integer obtainCodeNumber(String id) throws UserException{
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        return userOptional.get().getCodeNumber();
    }
}
