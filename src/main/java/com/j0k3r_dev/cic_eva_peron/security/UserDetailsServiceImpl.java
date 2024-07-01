package com.j0k3r_dev.cic_eva_peron.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.j0k3r_dev.cic_eva_peron.http.response.ActualizationResponse;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public void logOut(String id) throws UserException {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new UserException("User not found",492);
        userOptional.get().setCodeNumberLogin(null);
        userOptional.get().setCodeNumberActualization(null);
    }

    @Transactional
    public ActualizationResponse generateTokenToActualization(String token, String id) throws JWTVerificationException, UserException {
        String subject = jwtService.validateTokenToActualization(token, id);
        String token_actualization = jwtService.generateTokenToActualization(subject);
        String new_token = jwtService.generateTokenLogin(subject);
        return new ActualizationResponse(new_token, token_actualization);
    }

}
