package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.controllers.validationsLogin.ValidationsLogin;
import com.j0k3r_dev.cic_eva_peron.http.request.UserLoginRequest;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private List<ValidationsLogin> validationsLogins;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest userRequest) throws UserException {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(userRequest.getUsername());
        for(ValidationsLogin validation : validationsLogins){
            validation.validate(optionalUser);
        }
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        userRequest.getUsername(),
                        userRequest.getPassword());
        this.authenticationManager.authenticate(authenticationRequest).getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(userRequest.getUsername());
        return ResponseEntity.ok("success");
    }

}
