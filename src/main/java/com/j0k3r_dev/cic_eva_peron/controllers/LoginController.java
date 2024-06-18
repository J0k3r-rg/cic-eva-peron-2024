package com.j0k3r_dev.cic_eva_peron.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.j0k3r_dev.cic_eva_peron.controllers.validationsLogin.ValidationsLogin;
import com.j0k3r_dev.cic_eva_peron.http.request.ActualizationRequest;
import com.j0k3r_dev.cic_eva_peron.http.request.LogoutRequest;
import com.j0k3r_dev.cic_eva_peron.http.request.UserLoginRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.ActualizationResponse;
import com.j0k3r_dev.cic_eva_peron.http.response.LoginResponse;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.security.UserDetailsServiceImpl;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private List<ValidationsLogin> validationsLogins;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
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
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(userRequest.getUsername());
        String token = jwtService.generateTokenLogin(user.getUsername());
        String token_actualization = jwtService.generateTokenToActualization(user.getUsername());
        List<String> authorities = user.getAuthorities().stream().map(
                GrantedAuthority::getAuthority
        ).toList();
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .authorities(authorities)
                        .id(user.getId())
                        .email(user.getEmail())
                        .token(token)
                        .token_actualization(token_actualization)
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody @Valid LogoutRequest logoutRequest) throws UserException {
        userDetailsService.logOut(logoutRequest.getId());
        return ResponseEntity.ok("Logout");
    }

    @PostMapping("/actualization")
    public ResponseEntity<?> actualization(@RequestBody @Valid ActualizationRequest request) throws JWTVerificationException, UserException {
        ActualizationResponse response =
                userDetailsService.generateTokenToActualization(
                        request.getToken(),
                        request.getId()
                );
        return ResponseEntity.ok(response);
    }

}
