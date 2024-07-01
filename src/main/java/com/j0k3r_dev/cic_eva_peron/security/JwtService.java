package com.j0k3r_dev.cic_eva_peron.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.j0k3r_dev.cic_eva_peron.users.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Autowired
    private Environment environment;

    @Autowired
    private UserUtils userUtils;

    public String generateTokenLogin(String username) throws UserException {
        Integer codeNumber = userUtils.generateCodeNumberLogin(username);
        return generateToken(
                username, "Login Auth"+codeNumber,
                generateExpireDate(1), environment.getProperty("SECRET_KEY_TOKEN_LOGIN"));
    }

    public String validateTokenLogin(String token, String id) throws UserException {
        Integer codeNumber = userUtils.obtainCodeNumberLogin(id);
        try{
            return validateToken(
                    token,"Login Auth"+codeNumber,
                    environment.getProperty("SECRET_KEY_TOKEN_LOGIN"));
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String generateTokenToActualization(String username) throws UserException {
        Integer codeNumber = userUtils.generateCodeNumberActualization(username);
        return generateToken(
                username, "Actualization Auth "+codeNumber,
                generateExpireDate(60*24), environment.getProperty("SECRET_KEY_TOKEN_ACTUALIZATION"));
    }

    public String validateTokenToActualization(String token, String id) throws UserException {
        Integer codeNumber = userUtils.obtainCodeNumberActualization(id);
        try{
            return validateToken(
                    token,"Actualization Auth"+codeNumber,
                    environment.getProperty("SECRET_KEY_TOKEN_ACTUALIZATION"));
        } catch (JWTVerificationException e) {
            return null;
        }
    }


    private String generateToken(String subject, String issuer, Instant expiration, String secret_key) {
        Algorithm algorithm = Algorithm.HMAC256(secret_key);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    private String validateToken(String token, String issuer, String secret_key) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret_key);
        DecodedJWT verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build().verify(token);
        return verifier.getSubject();
    }

    private Instant generateExpireDate(int minutes){
        return LocalDateTime.now().plusHours(minutes).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getTokenAuthorization(String authorization){
        return authorization.replace("Bearer ","");
    }

    public String getAuthorization(HttpServletRequest request){
        return  request.getHeader("Authorization");
    }

}

